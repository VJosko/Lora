package com.vudrag.lorablok.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.vudrag.lorablok.async.InsertGameAsyncTask;
import com.vudrag.lorablok.async.UpdateGameAsyncTask;
import com.vudrag.lorablok.classes.Game;

import java.util.List;

public class GamesRepository {

    private GamesDatabase mGamesDatabase;

    public GamesRepository(Context context){
        mGamesDatabase = GamesDatabase.getInstance(context);
    }

    public void insertGameTask(Game game){
        new InsertGameAsyncTask(mGamesDatabase.getGamesDao()).execute(game);
    }

    public LiveData<List<Game>> retrieveGamesTask(){

        return mGamesDatabase.getGamesDao().getGames();
    }

    public void updateGame(Game game){
        new UpdateGameAsyncTask(mGamesDatabase.getGamesDao()).execute(game);
    }

    public void deleteGame(Game game){
    }
}
