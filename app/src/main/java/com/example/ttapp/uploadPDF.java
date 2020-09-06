package com.example.ttapp;

public class uploadPDF {
    private String name;
    private String lastdateofsubmission;
    private String subjectname;
    private String url;
    public uploadPDF() {
    }

    public uploadPDF(String name, String lastdateofsubmission, String subjectname, String url) {
        this.name = name;
        this.lastdateofsubmission = lastdateofsubmission;
        this.subjectname = subjectname;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getLastdateofsubmission() {
        return lastdateofsubmission;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getUrl() {
        return url;
    }
}
