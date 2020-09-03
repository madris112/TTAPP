package com.example.ttapp;

public class course {
    String id;
    String coursename;
    String instructorname;
    String link;
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

    public course(){

    }

    public course(String id, String coursename, String instructorname, String link, String mstart, String mend, String tustart, String tuend, String wedstart, String wedend, String thstart, String thend, String fristart, String friend) {
        this.id = id;
        this.coursename = coursename;
        this.instructorname = instructorname;
        this.link = link;
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

    public String getId() {
        return id;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getInstructorname() {
        return instructorname;
    }

    public String getLink() {
        return link;
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
