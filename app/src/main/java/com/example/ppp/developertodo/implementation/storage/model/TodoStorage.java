package com.example.ppp.developertodo.implementation.storage.model;

import com.example.ppp.developertodo.implementation.storage.database.MyDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by ppp on 2016-11-10.
 */

@Table(database = MyDatabase.class)
public class TodoStorage extends BaseModel {

    @PrimaryKey(autoincrement = true)
    public int id;


    @Column
    private String name;

    @Column
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
        this.duration = duration;
    }

    public TodoStorage(int id, String name, int duration) {
        this.id=id;
        this.duration = duration;
        this.name = name;
    }

    public TodoStorage( String name, int duration) {

        this.duration = duration;
        this.name = name;
    }


    public TodoStorage() {
    }
}
