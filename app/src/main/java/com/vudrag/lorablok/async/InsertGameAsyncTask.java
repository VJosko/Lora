package com.vudrag.lorablok.async;

import android.os.AsyncTask;

import com.vudrag.lorablok.classes.Game;
import com.vudrag.lorablok.room.GamesDao;

public class InsertGameAsyncTask extends AsyncTask<Game, Void, Void> {

    private GamesDao mGamesDao;

    public InsertGameAsyncTask(GamesDao dao){
        mGamesDao = dao;
    }

    @Override
    protected Void doInBackground(Game... games) {
        mGamesDao.insertGame(games);
        return null;
    }
}
