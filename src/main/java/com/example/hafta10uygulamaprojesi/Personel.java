package com.example.hafta10uygulamaprojesi;

public class Personel {
    private int sicilno;
    private String ad,soyad,depertman;

    public Personel(int sicilno, String ad, String soyad, String depertman) {
        this.sicilno = sicilno;
        this.ad = ad;
        this.soyad = soyad;
        this.depertman = depertman;
    }

    public int getSicilno() {
        return sicilno;
    }

    public void setSicilno(int sicilno) {
        this.sicilno = sicilno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDepertman() {
        return depertman;
    }

    public void setDepertman(String depertman) {
        this.depertman = depertman;
    }
}
