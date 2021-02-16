package com.lania.ejercicio2.componentes.editor;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.ejercicio2.dominio.dao.EditorDao;
import com.lania.ejercicio2.dominio.entidades.Editor;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class EditorViewModel extends ViewModel {

    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<String> direccion = new MutableLiveData<>();
    private final MutableLiveData<List<Editor>> editores = new MutableLiveData<>();

    private final MutableLiveData<Boolean> nombreValid = new MutableLiveData<>();
    private final MutableLiveData<Boolean> direccionValid = new MutableLiveData<>();


    public Editor createEditorByViewModel() {

        String nombre = this.nombre.getValue();
        String direccion = this.direccion.getValue();

        Editor editor = new Editor();
        editor.setNombre(nombre);
        editor.setDireccion(direccion);
        return editor;
    }


    public void clean() {
        this.nombre.setValue("");
        this.direccion.setValue("");
    }

}
