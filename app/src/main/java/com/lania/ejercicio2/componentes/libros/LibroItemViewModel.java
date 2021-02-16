package com.lania.ejercicio2.componentes.libros;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.ejercicio2.dominio.entidades.Libro;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LibroItemViewModel extends ViewModel {

    private MutableLiveData<Libro> data = new MutableLiveData<>();

}
