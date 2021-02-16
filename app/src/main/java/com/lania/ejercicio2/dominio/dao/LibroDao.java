package com.lania.ejercicio2.dominio.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lania.ejercicio2.dominio.entidades.Libro;

import java.util.List;

@Dao
public interface LibroDao {

    @Query("SELECT * FROM libros")
    List<Libro> getAllLibros();

    @Insert
    List<Long> insertAll(Libro... libros);

    @Delete
    void deleteBook(Libro libro);

    @Query("SELECT count(*) FROM libros")
    Long count();

}
