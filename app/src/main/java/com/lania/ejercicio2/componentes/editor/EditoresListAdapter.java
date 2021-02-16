package com.lania.ejercicio2.componentes.editor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingUtil;

import com.lania.ejercicio2.R;
import com.lania.ejercicio2.databinding.EditorItemBinding;
import com.lania.ejercicio2.dominio.entidades.Editor;

public class EditoresListAdapter extends ArrayAdapter<Editor> {

    private final EditorController editorController;
    private final Activity activity;

    public EditoresListAdapter(@NonNull Activity activity, EditorController editorController) {
        super(activity, R.layout.editor_item);
        this.editorController = editorController;
        this.activity = activity;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        EditorItemBinding editorItemBinding = DataBindingUtil.inflate(inflater, R.layout.editor_item, parent, false);
        Editor editor = this.getItem(position);
        EditorItemViewModel editorItemViewModel = new EditorItemViewModel(editor);
        editorItemBinding.setEditor(editorItemViewModel);
        View view = editorItemBinding.getRoot();
        AppCompatImageButton imageButton = view.findViewById(R.id.editor_borrar_button);
        imageButton.setTag(editor);
        imageButton.setOnClickListener(this.editorController);
        return view;
    }
}
