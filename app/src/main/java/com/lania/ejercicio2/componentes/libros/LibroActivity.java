package com.lania.ejercicio2.componentes.libros;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.lania.ejercicio2.MainActivity;
import com.lania.ejercicio2.R;
import com.lania.ejercicio2.databinding.ActivityLibroBinding;
import com.lania.ejercicio2.dominio.AppDatabase;
import com.lania.ejercicio2.dominio.dao.LibroDao;
import com.lania.ejercicio2.utils.DatabaseProvider;

public class LibroActivity extends AppCompatActivity {

    private static final String TAG = "LibroActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDatabase appDatabase = DatabaseProvider.getDatabase(this.getApplicationContext());
        LibroDao libroDao = appDatabase.libroDao();

        LibroViewModel libroViewModel = new ViewModelProvider(this).get(LibroViewModel.class);
        libroViewModel.setLibroDao(libroDao);

        ActivityLibroBinding activityLibroBinding = DataBindingUtil.setContentView(this, R.layout.activity_libro);
        activityLibroBinding.setLibro(libroViewModel);
        activityLibroBinding.setLifecycleOwner(this);

        MaterialToolbar toolbar = findViewById(R.id.libroToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LibroController libroController = new LibroController(this, libroViewModel, libroDao);
        LibrosListAdapter librosListAdapter = new LibrosListAdapter(this, libroController);

        Button registerLibroButton = findViewById(R.id.register_libro);
        ListView listView = findViewById(R.id.libro_list_view);
        listView.setAdapter(librosListAdapter);

        this.createValidations(libroViewModel);
        libroViewModel.getLibros().observe(this, libros -> {
            librosListAdapter.clear();
            librosListAdapter.addAll(libros);
            librosListAdapter.notifyDataSetChanged();
        });

        registerLibroButton.setOnClickListener(libroController);
    }


    private void createValidations(LibroViewModel libroViewModel) {
        libroViewModel.getTitulo().observe(this, value -> this.emptyFieldValidator(value, R.id.editor_nombre_field, libroViewModel.getTituloValid()));
        libroViewModel.getDisponibilidad().observe(this, value -> this.emptyFieldValidator(value, R.id.editor_direccion_field, libroViewModel.getDisponibilidadValid()));
        libroViewModel.getPrecio().observe(this, value -> this.emptyFieldValidator(value, R.id.precio_field, libroViewModel.getPrecioValid()));
        libroViewModel.getAutor().observe(this, value -> this.emptyFieldValidator(value, R.id.autor_field, libroViewModel.getAutorValid()));
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