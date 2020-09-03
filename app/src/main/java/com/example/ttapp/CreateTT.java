package com.example.ttapp;

public class CreateTT {
    String id;
    String mstart;
    String mend;
    String tustart;
    String tuend;
    String wedstart;
    String wedend;
    String thstart;
    String thend;
    String fristart;
    String friend;

    public CreateTT(){

    }

    public CreateTT(String id,String mstart, String mend, String tustart, String tuend, String wedstart, String wedend, String thstart, String thend, String fristart, String friend) {
        this.id = id;
        this.mstart = mstart;
        this.mend = mend;
        this.tustart = tustart;
        this.tuend = tuend;
        this.wedstart = wedstart;
        this.wedend = wedend;
        this.thstart = thstart;
        this.thend = thend;
        this.fristart = fristart;
        this.friend = friend;
    }

    public String getId(){
        return id;
    }

    public String getMstart() {
        return mstart;
    }

    public String getMend() {
        return mend;
    }

    public String getTustart() {
        return tustart;
    }

    public String getTuend() {
        return tuend;
    }

    public String getWedstart() {
        return wedstart;
    }

    public String getWedend() {
        return wedend;
    }

    public String getThstart() {
        return thstart;
    }

    public String getThend() {
        return thend;
    }

    public String getFristart() {
        return fristart;
    }

    public String getFriend() {
        return friend;
    }
}
