package com.vudrag.lorablok.async;

import android.os.AsyncTask;

import com.vudrag.lorablok.classes.Game;
import com.vudrag.lorablok.room.GamesDao;

public class UpdateGameAsyncTask extends AsyncTask<Game, Void, Void> {

    private GamesDao mGamesDao;

    public  UpdateGameAsyncTask(GamesDao dao){
        mGamesDao = dao;
    }

    @Override
    protected Void doInBackground(Game... games) {
        mGamesDao.update(games);
        return null;
    }
}
