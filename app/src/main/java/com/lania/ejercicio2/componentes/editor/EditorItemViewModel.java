package com.lania.ejercicio2.componentes.editor;

import androidx.lifecycle.ViewModel;

import com.lania.ejercicio2.dominio.entidades.Editor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EditorItemViewModel extends ViewModel {
    private final Editor data;
}
