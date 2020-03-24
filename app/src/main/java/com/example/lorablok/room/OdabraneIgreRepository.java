package com.example.lorablok.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.lorablok.async.DeleteIgreAcyncTask;
import com.example.lorablok.async.InsertIgreAcyncTask;
import com.example.lorablok.async.UpdateIgreAsyncTask;
import com.example.lorablok.classes.Igra;

import java.util.ArrayList;
import java.util.List;

public class OdabraneIgreRepository {

    private OdabraneIgreDatabase mOdabraneIgreDatabase;

    public OdabraneIgreRepository(Context context) {
        mOdabraneIgreDatabase = OdabraneIgreDatabase.getInstance(context);
    }

    public void insertIgraTask(Igra igra){
        new InsertIgreAcyncTask(mOdabraneIgreDatabase.getIgreDao()).execute(igra);
    }
    
    public void inserIgraListTask(ArrayList<Igra> igre){
        for (Igra igra: igre) {
            new InsertIgreAcyncTask(mOdabraneIgreDatabase.getIgreDao()).execute(igra);
        }
    }

    public LiveData<List<Igra>> retrieveIgreTask(){

        return mOdabraneIgreDatabase.getIgreDao().getIgre();
    }

    public void updateIgra(Igra... igra){
        new UpdateIgreAsyncTask(mOdabraneIgreDatabase.getIgreDao()).execute(igra);
    }

    public void deleteIgra(Igra igra){
        new DeleteIgreAcyncTask(mOdabraneIgreDatabase.getIgreDao()).execute(igra);
    }

    public void deleteIgraList(ArrayList<Igra> igre){
        for (Igra igra: igre) {
            new DeleteIgreAcyncTask(mOdabraneIgreDatabase.getIgreDao()).execute(igra);
        }
    }
}
