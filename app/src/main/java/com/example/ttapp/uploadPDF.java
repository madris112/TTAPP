package com.example.ttapp;

public class uploadPDF {
    private String name;
    private String uri;

    public uploadPDF() {
    }

    public uploadPDF(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }


    public String getUri() {
        return uri;
    }


}
