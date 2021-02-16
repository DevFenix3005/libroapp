package com.lania.ejercicio2.dominio;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.lania.ejercicio2.dominio.convertes.DateConvertes;
import com.lania.ejercicio2.dominio.dao.EditorDao;
import com.lania.ejercicio2.dominio.dao.LibroDao;
import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.dominio.entidades.Editor;
import com.lania.ejercicio2.dominio.entidades.Libro;
import com.lania.ejercicio2.dominio.entidades.Miembro;

@Database(entities = {Libro.class, Miembro.class, Editor.class}, version = 7, exportSchema = false)
@TypeConverters({DateConvertes.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract LibroDao libroDao();

    public abstract MiembroDao miembroDao();

    public abstract EditorDao editorDao();

}
