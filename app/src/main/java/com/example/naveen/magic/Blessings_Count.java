package com.example.naveen.magic;

import java.io.Serializable;

public class Blessings_Count implements Serializable {
    private String itemnumber;
    private String blessing;
    private final String _id;

    public Blessings_Count(String id) {
        _id = id;
    }

    public String getItemnumber() {
        return itemnumber;
    }

    public void setItemnumber(String itemnumber) {
        this.itemnumber = itemnumber;
    }

    public String getBlessing() {
        return blessing;
    }

    public void setBlessing(String blessing) {
        this.blessing = blessing;
    }

    public String get_id() {
        return _id;
    }
}
