package com.vudrag.lorablok.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vudrag.lorablok.classes.Igra;

@Database(entities = {Igra.class},version = 3)
public abstract class IgreDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "igre_db";

    private static IgreDatabase instance;

    static IgreDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    IgreDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract IgreDao getIgreDao();
}
