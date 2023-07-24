package com.example.appbandoan.model;

public class Donhang {
    int id;
    String username,trangthai,tongtien,tienphichuyen,diachinhan,ghichu,date;

    public Donhang() {
    }

    public Donhang(int id, String username, String trangthai, String tongtien, String tienphichuyen, String diachinhan, String ghichu, String date) {
        this.id = id;
        this.username = username;
        this.trangthai = trangthai;
        this.tongtien = tongtien;
        this.tienphichuyen = tienphichuyen;
        this.diachinhan = diachinhan;
        this.ghichu = ghichu;
        this.date = date;
    }

    public Donhang(String username, String trangthai, String tongtien, String tienphichuyen, String diachinhan, String ghichu, String date) {
        this.username = username;
        this.trangthai = trangthai;
        this.tongtien = tongtien;
        this.tienphichuyen = tienphichuyen;
        this.diachinhan = diachinhan;
        this.ghichu = ghichu;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTongtien() {
        return tongtien;
    }

    public void setTongtien(String tongtien) {
        this.tongtien = tongtien;
    }

    public String getTienphichuyen() {
        return tienphichuyen;
    }

    public void setTienphichuyen(String tienphichuyen) {
        this.tienphichuyen = tienphichuyen;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
