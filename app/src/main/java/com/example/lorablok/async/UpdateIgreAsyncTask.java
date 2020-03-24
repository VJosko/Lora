package com.example.lorablok.async;

import android.os.AsyncTask;

import com.example.lorablok.classes.Igra;
import com.example.lorablok.room.IgreDao;

public class UpdateIgreAsyncTask extends AsyncTask<Igra, Void, Void> {

    private IgreDao mIgreDao;

    public UpdateIgreAsyncTask(IgreDao dao) {
        mIgreDao = dao;
    }

    @Override
    protected Void doInBackground(Igra... igras) {
        mIgreDao.update(igras);
        return null;
    }
}
