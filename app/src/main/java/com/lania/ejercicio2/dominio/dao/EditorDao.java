package com.lania.ejercicio2.dominio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lania.ejercicio2.dominio.entidades.Editor;
import com.lania.ejercicio2.dominio.entidades.Libro;

import java.util.List;

@Dao
public interface EditorDao {

    @Query("SELECT * FROM editores")
    List<Editor> getAllEditores();

    @Query("SELECT count(*) FROM editores")
    Long count();

    @Insert
    List<Long> insertAll(Editor... editores);

    @Insert
    Long insertOne(Editor editor);

    @Delete
    void deleteEditores(Editor editor);

}
