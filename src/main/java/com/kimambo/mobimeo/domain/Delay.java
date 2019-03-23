package com.kimambo.mobimeo.domain;

public class Delay {

    private String name;
    private int minutes;

    public Delay(String name, int min){
        this.name = name;
        minutes = min;
    }
    public String getName() {
        return name;
    }

    public int getMinutes() {
        return minutes;
    }

}
