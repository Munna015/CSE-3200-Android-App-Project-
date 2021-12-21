package com.example.engineering_books;

public class UploadHandler {
    String pdfName;
    String PdfUrl;

    public UploadHandler() {
    }

    public UploadHandler(String pdfName, String pdfUrl) {
        this.pdfName = pdfName;
        PdfUrl = pdfUrl;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfUrl() {
        return PdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        PdfUrl = pdfUrl;
    }
}
