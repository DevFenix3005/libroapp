package com.lania.ejercicio2.componentes.editor;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.lania.ejercicio2.R;
import com.lania.ejercicio2.dominio.dao.EditorDao;
import com.lania.ejercicio2.dominio.entidades.Editor;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EditorController implements View.OnClickListener {

    @NonNull
    private final EditorActivity activity;
    @NonNull
    private final EditorDao editorDao;
    @NonNull
    private final EditorViewModel editorViewModel;


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.register_editor:
                registarEditor();
                break;
            case R.id.editor_borrar_button:
                borrarEditor(v);
                break;

        }

    }

    private void borrarEditor(View view) {
        Editor editor = (Editor) view.getTag();
        editorDao.deleteEditores(editor);
        Toast.makeText(this.activity, String.format(Locale.getDefault(), "Se a borrado el editor %s con el di %d", editor.getNombre(), editor.getId()), Toast.LENGTH_LONG).show();

        MutableLiveData<List<Editor>> mutableList = editorViewModel.getEditores();
        List<Editor> editorList = mutableList.getValue();
        List<Editor> newEditorList = new ArrayList<>(editorList);
        newEditorList.remove(editor);
        mutableList.setValue(newEditorList);


    }

    private void registarEditor() {
        Editor editor = editorViewModel.createEditorByViewModel();
        Long id = editorDao.insertOne(editor);
        Toast.makeText(this.activity, String.format(Locale.getDefault(), "Se agrego el editor %s con el di %d", editor.getNombre(), id), Toast.LENGTH_LONG).show();
        MutableLiveData<List<Editor>> mutableList = editorViewModel.getEditores();
        List<Editor> editorList = mutableList.getValue();
        List<Editor> newEditorList = new ArrayList<>(editorList);
        newEditorList.add(editor);
        mutableList.setValue(newEditorList);
        editorViewModel.clean();

    }
}
