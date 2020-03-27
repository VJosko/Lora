package com.vudrag.lorablok.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Games")
public class Game {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    int id;

    @ColumnInfo
    String igrac1;

    @ColumnInfo
    String igrac2;

    @ColumnInfo
    String igrac3;

    @ColumnInfo
    String igrac4;

    @ColumnInfo
    int rezultat1;

    @ColumnInfo
    int rezultat2;

    @ColumnInfo
    int rezultat3;

    @ColumnInfo
    int rezultat4;

    public Game(String igrac1, String igrac2, String igrac3, String igrac4, int rezultat1, int rezultat2, int rezultat3, int rezultat4){
        this.igrac1 = igrac1;
        this.igrac2 = igrac2;
        this.igrac3 = igrac3;
        this.igrac4 = igrac4;
        this.rezultat1 = rezultat1;
        this.rezultat2 = rezultat2;
        this.rezultat3 = rezultat3;
        this.rezultat4 = rezultat4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIgrac1() {
        return igrac1;
    }

    public void setIgrac1(String igrac1) {
        this.igrac1 = igrac1;
    }

    public String getIgrac2() {
        return igrac2;
    }

    public void setIgrac2(String igrac2) {
        this.igrac2 = igrac2;
    }

    public String getIgrac3() {
        return igrac3;
    }

    public void setIgrac3(String igrac3) {
        this.igrac3 = igrac3;
    }

    public String getIgrac4() {
        return igrac4;
    }

    public void setIgrac4(String igrac4) {
        this.igrac4 = igrac4;
    }

    public int getRezultat1() {
        return rezultat1;
    }

    public void setRezultat1(int rezultat1) {
        this.rezultat1 = rezultat1;
    }

    public int getRezultat2() {
        return rezultat2;
    }

    public void setRezultat2(int rezultat2) {
        this.rezultat2 = rezultat2;
    }

    public int getRezultat3() {
        return rezultat3;
    }

    public void setRezultat3(int rezultat3) {
        this.rezultat3 = rezultat3;
    }

    public int getRezultat4() {
        return rezultat4;
    }

    public void setRezultat4(int rezultat4) {
        this.rezultat4 = rezultat4;
    }
}
