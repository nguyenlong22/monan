package com.example.appbandoan.model;

public class donhangmonan {
    int id,iddonhang,idmonan;
    String soluongmonan,giatienmonan;

    public donhangmonan() {
    }

    public donhangmonan(int id, int iddonhang, int idmonan, String soluongmonan, String giatienmonan) {
        this.id = id;
        this.iddonhang = iddonhang;
        this.idmonan = idmonan;
        this.soluongmonan = soluongmonan;
        this.giatienmonan = giatienmonan;
    }

    public donhangmonan(int iddonhang, int idmonan, String soluongmonan, String giatienmonan) {
        this.iddonhang = iddonhang;
        this.idmonan = idmonan;
        this.soluongmonan = soluongmonan;
        this.giatienmonan = giatienmonan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIddonhang() {
        return iddonhang;
    }

    public void setIddonhang(int iddonhang) {
        this.iddonhang = iddonhang;
    }

    public int getIdmonan() {
        return idmonan;
    }

    public void setIdmonan(int idmonan) {
        this.idmonan = idmonan;
    }

    public String getSoluongmonan() {
        return soluongmonan;
    }

    public void setSoluongmonan(String soluongmonan) {
        this.soluongmonan = soluongmonan;
    }

    public String getGiatienmonan() {
        return giatienmonan;
    }

    public void setGiatienmonan(String giatienmonan) {
        this.giatienmonan = giatienmonan;
    }
}
