package com.example.ppp.developertodo.logic.model;

/**
 * Created by ppp on 2016-11-10.
 */

public class Todo {
    private int id;
    private String name;
    private int duration;

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

    public int getDuration() {

        return duration;
    }

    public void setDuration(int duration) {
        this.duration =  duration;
    }

    public Todo(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }
}
