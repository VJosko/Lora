package com.vudrag.lorablok.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vudrag.lorablok.classes.Game;

@Database(entities = {Game.class},version = 1)
public abstract class GamesDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "games_db";

    private static GamesDatabase instance;

    static GamesDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    GamesDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    public abstract GamesDao getGamesDao();
}
