package com.dormhub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "konfigurasi")
public class Konfigurasi {

    @Id
    private int id;

    private String favicon;
    private String namaWebsite;
    private String namaGedung;
    private String logo;
    private int lantai;
    private int kamar;
    private int kasur;
    private String footer;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getNamaWebsite() {
        return namaWebsite;
    }

    public void setNamaWebsite(String namaWebsite) {
        this.namaWebsite = namaWebsite;
    }

    public String getNamaGedung() {
        return namaGedung;
    }

    public void setNamaGedung(String namaGedung) {
        this.namaGedung = namaGedung;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getLantai() {
        return lantai;
    }

    public void setLantai(int lantai) {
        this.lantai = lantai;
    }

    public int getKamar() {
        return kamar;
    }

    public void setKamar(int kamar) {
        this.kamar = kamar;
    }

    public int getKasur() {
        return kasur;
    }

    public void setKasur(int kasur) {
        this.kasur = kasur;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
