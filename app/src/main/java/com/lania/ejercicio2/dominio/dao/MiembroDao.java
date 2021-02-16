package com.lania.ejercicio2.dominio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lania.ejercicio2.dominio.entidades.Miembro;

import java.util.List;

@Dao
public interface MiembroDao {

    @Query("SELECT * FROM miembros")
    List<Miembro> getAllMiembros();

    @Insert
    Long insertOne(Miembro miembro);

    @Insert
    List<Long> insertAll(Miembro... miembros);

    @Delete
    void deleteMiembro(Miembro miembro);

    @Query("SELECT count(*) FROM miembros")
    Long count();

}
