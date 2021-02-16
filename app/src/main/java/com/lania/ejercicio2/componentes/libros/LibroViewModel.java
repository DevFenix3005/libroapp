package com.lania.ejercicio2.componentes.libros;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.ejercicio2.dominio.dao.LibroDao;
import com.lania.ejercicio2.dominio.entidades.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibroViewModel extends ViewModel {

    private LibroDao libroDao;

    private final MutableLiveData<String> titulo = new MutableLiveData<>();
    private final MutableLiveData<String> disponibilidad = new MutableLiveData<>();
    private final MutableLiveData<String> precio = new MutableLiveData<>();
    private final MutableLiveData<String> autor = new MutableLiveData<>();
    private MutableLiveData<List<Libro>> libros;

    private final MutableLiveData<Boolean> tituloValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> disponibilidadValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> precioValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> autorValid = new MutableLiveData<>(Boolean.FALSE);

    public MutableLiveData<List<Libro>> getLibros() {
        if (this.libros == null) {
            this.libros = new MutableLiveData<>();
            this.loadLibros();
        }
        return libros;
    }

    private void loadLibros() {
        this.libros.setValue(this.libroDao.getAllLibros());
    }

    public Libro createEntityFromView() {

        String titulo = this.titulo.getValue();
        String disponibilidad = this.disponibilidad.getValue();
        String precio = this.precio.getValue();
        String autor = this.autor.getValue();

        Libro libro = new Libro();
        libro.setNombre(titulo);
        libro.setDisponibilidad(Integer.parseInt(disponibilidad));
        libro.setPrecio(Float.parseFloat(precio));
        libro.setAutor(autor);

        return libro;
    }


    public void clean() {
        titulo.setValue("");
        disponibilidad.setValue("");
        precio.setValue("");
        autor.setValue("");
    }
}
