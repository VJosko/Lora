package com.vudrag.lorablok.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vudrag.lorablok.classes.Game;

import java.util.List;

@Dao
public interface GamesDao {

    @Insert
    long[] insertGame(Game... games);

    @Query("Select * from Games")
    LiveData<List<Game>> getGames();

    @Delete
    int delete(Game... games);

    @Update
    int update(Game... games);
}
