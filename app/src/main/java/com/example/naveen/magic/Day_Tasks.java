package com.example.naveen.magic;

import java.io.Serializable;

public class Day_Tasks implements Serializable {
    private static final long serialVersionUID=1L;

    private String day_number,task_number;
    private int progress;
    private String day_name;

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getDay_number() {
        return day_number;
    }

    public void setDay_number(String day_number) {
        this.day_number = day_number;
    }

    public String getTask_number() {
        return task_number;
    }

    public void setTask_number(String task_number) {
        this.task_number = task_number;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
