package com.example.horses.Dataverwerking;

//enkel gemaakt omdat Date kut deed

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Datum implements Comparable<Datum> {
    private int dag;
    private int maand;
    private int jaar;
    public Datum (LocalDate x){
        dag = x.getDayOfMonth();
        maand = x.getMonthValue();
        jaar = x.getYear();
    }

    public Datum(String datum){
        Scanner sc = new Scanner(datum);
        sc.useDelimiter("/");
        dag = sc.nextInt();
        maand = sc.nextInt();
        jaar = sc.nextInt();
    }

    public int getDag(){
        return dag;
    }

    public int getJaar() {
        return jaar;
    }

    public int getMaand() {
        return maand;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        if (dag < 10){
            sb.append("0");
        }

        sb.append(dag).append("/");

        if (maand < 10){
            sb.append("0");
        }

        sb.append(maand).append("/").append(jaar);

        return sb.toString();
    }

    public boolean datumInInterval(Datum begin,Datum eind){
        //tijdelijke oplossing
        begin.dag -= 1;
        if (groterDan(this,begin) && !groterDan(this,eind)){
            return  true;
        }
        return false;
    }

    private boolean groterDan(Datum x,Datum y){
        if (x.jaar > y.jaar){
            return true;
        }
        else if (x.jaar == y.jaar){
            if (x.maand > y.maand){
                return true;
            }
            else  if (x.maand == y.maand){
                if (x.dag > y.dag){
                    return true;
                }
            }
        }
        return  false;
    }

    @Override
    public int compareTo(Datum o) {
        if (this.jaar != o.jaar){
            return this.jaar - o.jaar;
        }
        if (this.maand != o.maand){
            return this.maand - o.maand;
        }
        return this.dag - o.dag;
    }
}
