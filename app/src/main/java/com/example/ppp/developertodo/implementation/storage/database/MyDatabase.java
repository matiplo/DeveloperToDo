package com.example.ppp.developertodo.implementation.storage.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ppp on 2016-11-10.
 */

@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME="MyDatabase";
    public static final int VERSION=3;
}
