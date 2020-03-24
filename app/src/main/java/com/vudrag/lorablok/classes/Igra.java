package com.vudrag.lorablok.classes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "igre")
public class Igra {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "opis")
    public String opis;

    @ColumnInfo(name = "bodovanje")
    public int bodovanje;

    @ColumnInfo(name = "redosljed")
    public int red;

    public boolean expanded = false;

    public Igra(String name, String opis, int bodovanje, int red){
        this.name = name;
        this.opis = opis;
        this.bodovanje = bodovanje;
        this.red = red;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExpanded(){
        return expanded;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBodovanje() {
        return bodovanje;
    }

    public void setBodovanje(int bodovanje) {
        this.bodovanje = bodovanje;
    }
}
