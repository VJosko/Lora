package com.vudrag.lorablok.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.vudrag.lorablok.async.DeleteIgreAcyncTask;
import com.vudrag.lorablok.async.InsertIgreAcyncTask;
import com.vudrag.lorablok.classes.Igra;

import java.util.List;

public class IgreRepository {

    private IgreDatabase mIgraDatabase;

    public IgreRepository(Context context) {
        mIgraDatabase = IgreDatabase.getInstance(context);
    }

    public void insertIgraTask(Igra igra){
        new InsertIgreAcyncTask(mIgraDatabase.getIgreDao()).execute(igra);
    }

    public LiveData<List<Igra>> retrieveIgreTask(){

        return mIgraDatabase.getIgreDao().getIgre();
    }

    public void updateIgra(Igra igra){

    }

    public void deleteIgra(Igra igra){
        new DeleteIgreAcyncTask(mIgraDatabase.getIgreDao()).execute(igra);
    }
}
