package com.vudrag.lorablok.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vudrag.lorablok.classes.Igra;

import java.util.List;

@Dao
public interface IgreDao {

    @Insert
    long[] insertIgre(Igra... igra);

    @Query("Select * from Igre")
    LiveData<List<Igra>> getIgre();

    @Delete
    int delete(Igra... igra);

    @Update
    int update(Igra... igra);
}
