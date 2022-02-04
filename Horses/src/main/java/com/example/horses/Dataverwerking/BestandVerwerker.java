package com.example.horses.Dataverwerking;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestandVerwerker {
    ArrayList<Betaling> betaling;

    public BestandVerwerker(){
        betaling = new ArrayList<>();
    }

    public void leesBestandIn(File bestand) throws FileNotFoundException {
        Scanner sc = new Scanner(bestand);
        Scanner lijnSplitser;

        //Eerste lijn met zever weggooien
        sc.nextLine();

        while (sc.hasNext()){
            //alles wat er nodig is om de string te parsen
            lijnSplitser = new Scanner(sc.nextLine());
            lijnSplitser.useDelimiter(";");

            //benodigde string
            String datum;
            String naam;
            String bedrag;
            String mededeling;

            lijnSplitser.next();

            datum = lijnSplitser.next();

            lijnSplitser.next();
            lijnSplitser.next();
            lijnSplitser.next();

            naam = lijnSplitser.next();

            lijnSplitser.next();
            lijnSplitser.next();
            lijnSplitser.next();
            lijnSplitser.next();

            //replace is voor een juiste double parse in Betaling
            bedrag = lijnSplitser.next().replace(',','.');

            lijnSplitser.next();
            lijnSplitser.next();
            lijnSplitser.next();

            mededeling = (lijnSplitser.hasNext())? lijnSplitser.next():"geen mededeling";

            betaling.add(new Betaling(datum,naam,mededeling,bedrag));
        }

    }

    public List<Betaling> AllesTussen(Datum begin,Datum eind){
        List<Betaling> dataLijst = new ArrayList<>();
        for (int i = 0; i<betaling.size();i++){
            if (betaling.get(i).getDatum().datumInInterval(begin,eind)){
                dataLijst.add(betaling.get(i));
            }
        }
        return dataLijst;
    }

    public List<Betaling> bedragX(int x){
        List<Betaling> bedragLijst = new ArrayList<>();
        for (int i = 0; i<betaling.size();i++){
            if (betaling.get(i).getBedrag()%x == 0) {
                bedragLijst.add(betaling.get(i));
            }
        }
        return bedragLijst;
    }

    public List<Betaling> mededelingX(String x){
        List<Betaling> mededelingLijst = new ArrayList<>();

        for (int i = 0; i<betaling.size();i++){
            if (betaling.get(i).getMededeling().contains(x)) {
                mededelingLijst.add(betaling.get(i));
            }
        }
        return mededelingLijst;
    }

    public ArrayList<Betaling> getList(){
        return betaling;
    }

    //tester
    public void printAlles(){
        for (int i = 0; i<betaling.size();i++){
            System.out.println(betaling.get(i));
        }
    }

}
