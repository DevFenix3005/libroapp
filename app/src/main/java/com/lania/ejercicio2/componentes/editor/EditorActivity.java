package com.lania.ejercicio2.componentes.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.lania.ejercicio2.MainActivity;
import com.lania.ejercicio2.R;
import com.lania.ejercicio2.componentes.miembro.MiembroListAdapter;
import com.lania.ejercicio2.componentes.miembro.MiembrosViewModel;
import com.lania.ejercicio2.databinding.ActivityEditorBinding;
import com.lania.ejercicio2.databinding.ActivityMiembrosBinding;
import com.lania.ejercicio2.dominio.AppDatabase;
import com.lania.ejercicio2.dominio.dao.EditorDao;
import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.dominio.entidades.Editor;
import com.lania.ejercicio2.dominio.entidades.Libro;
import com.lania.ejercicio2.utils.DatabaseProvider;

import java.util.List;


public class EditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase appDatabase = DatabaseProvider.getDatabase(this.getApplicationContext());
        EditorDao editorDao = appDatabase.editorDao();
        List<Editor> initList = editorDao.getAllEditores();

        EditorViewModel editorViewModel = new ViewModelProvider(this).get(EditorViewModel.class);

        ActivityEditorBinding activityEditorBinding = DataBindingUtil.setContentView(this, R.layout.activity_editor);
        activityEditorBinding.setEditor(editorViewModel);
        activityEditorBinding.setLifecycleOwner(this);

        EditorController editorController = new EditorController(this, editorDao, editorViewModel);

        Button button = findViewById(R.id.register_editor);
        button.setOnClickListener(editorController);

        ListView listView = findViewById(R.id.editores_listview);
        EditoresListAdapter editoresListAdapter = new EditoresListAdapter(this, editorController);
        listView.setAdapter(editoresListAdapter);
        editorViewModel.getEditores().observe(this, editores -> {
            editoresListAdapter.clear();
            editoresListAdapter.addAll(editores);
            editoresListAdapter.notifyDataSetChanged();
        });

        editorViewModel.getEditores().setValue(initList);
        createValidations(editorViewModel);

        MaterialToolbar toolbar = findViewById(R.id.editorToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void createValidations(EditorViewModel editorViewModel) {
        editorViewModel.getNombre().observe(this, value -> this.emptyFieldValidator(value, R.id.editor_nombre_field, editorViewModel.getNombreValid()));
        editorViewModel.getDireccion().observe(this, value -> this.emptyFieldValidator(value, R.id.editor_direccion_field, editorViewModel.getDireccionValid()));
    }

    private void emptyFieldValidator(String value, int fieldId, MutableLiveData<Boolean> validFlag) {
        TextInputLayout textInputLayout = findViewById(fieldId);

        if (value.isEmpty()) {
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("No puede ser un campo vacio");
            validFlag.setValue(Boolean.FALSE);
        } else {
            textInputLayout.setError("");
            textInputLayout.setErrorEnabled(false);
            validFlag.setValue(Boolean.TRUE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
        }
        return (super.onOptionsItemSelected(menuItem));
    }
}