package com.example.ttapp;

public class PDFAdder {
    private String pdfname;
    private String deadline;

    public PDFAdder() {
    }

    public PDFAdder(String pdfname, String deadline) {
        this.pdfname = pdfname;
        this.deadline = deadline;
    }

    public String getPdfname() {
        return pdfname;
    }

    public void setPdfname(String pdfname) {
        this.pdfname = pdfname;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
