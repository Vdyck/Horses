package com.example.horses.Dataverwerking;

import java.util.Date;

public class Betaling {
    private Datum datum;
    private String naam;
    private String mededeling;
    private double bedrag;

    public Betaling(String date,String name,String med,String bed){
        datum = new Datum(date);
        naam = name;
        bedrag = Double.parseDouble(bed);
        mededeling = med;
    }

    public double getBedrag(){
        return bedrag;
    }

    public String getMededeling(){
        return mededeling;
    }

    public Datum getDatum() {
        return datum;
    }

    public String getNaam(){
        return naam;
    }


    @Override
    public String toString(){
        return datum + " " + naam + " " + bedrag + " " + mededeling;
    }
}
