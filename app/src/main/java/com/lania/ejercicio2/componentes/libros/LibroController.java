package com.lania.ejercicio2.componentes.libros;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.android.material.textfield.TextInputLayout;
import com.lania.ejercicio2.R;
import com.lania.ejercicio2.dominio.AppDatabase;
import com.lania.ejercicio2.dominio.dao.LibroDao;
import com.lania.ejercicio2.dominio.entidades.Libro;
import com.lania.ejercicio2.utils.DatabaseProvider;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import lombok.Data;
import lombok.NonNull;

@Data
public class LibroController implements View.OnClickListener {

    @NonNull
    private final LibroActivity libroActivity;

    @NonNull
    private final LibroViewModel model;

    private final LibroDao libroDao;

    public LibroController(@NonNull LibroActivity libroActivity, @NonNull LibroViewModel model, @NonNull LibroDao libroDao) {
        this.libroActivity = libroActivity;
        this.model = model;
        this.libroDao = libroDao;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_libro:
                registerBook();
                break;
            case R.id.libro_borrar_button:
                deleteBook((Libro) v.getTag());
                break;
        }
    }


    private void registerBook() {
        Libro libro = this.model.createEntityFromView();
        List<Long> idsList = libroDao.insertAll(libro);
        Long newId = idsList.get(0);
        libro.setId(newId);

        MutableLiveData<List<Libro>> libros = this.model.getLibros();

        List<Libro> libroList = libros.getValue();
        if (libroList != null) {
            libroList.add(libro);
            this.model.getLibros().setValue(libroList);

            String mensaje = String.format(Locale.getDefault(), "El libro '%s' fue creado y se le asigno el folio %d", libro.getNombre(), newId);
            Toast.makeText(libroActivity, mensaje, Toast.LENGTH_LONG).show();
            model.clean();
        }
    }

    private void deleteBook(Libro libro) {

        this.libroDao.deleteBook(libro);

        MutableLiveData<List<Libro>> libros = this.model.getLibros();
        List<Libro> libroList = libros.getValue();
        if (libroList != null) {
            libroList.remove(libro);
            this.model.getLibros().setValue(libroList);
            String mensaje = String.format(Locale.getDefault(), "El libro '%s' fue borrado", libro.getNombre());
            Toast.makeText(libroActivity, mensaje, Toast.LENGTH_LONG).show();
        }

    }


}
