package com.lania.ejercicio2.componentes.miembro;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.lania.ejercicio2.MainActivity;
import com.lania.ejercicio2.R;
import com.lania.ejercicio2.databinding.ActivityMiembrosBinding;
import com.lania.ejercicio2.dominio.AppDatabase;
import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.utils.DatabaseProvider;

public class MiembrosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDatabase appDatabase = DatabaseProvider.getDatabase(this.getApplicationContext());
        MiembroDao miembroDao = appDatabase.miembroDao();

        MiembrosViewModel miembrosViewModel = new ViewModelProvider(this).get(MiembrosViewModel.class);
        miembrosViewModel.setMiembroDao(miembroDao);

        ActivityMiembrosBinding activityMiembrosBinding = DataBindingUtil.setContentView(this, R.layout.activity_miembros);
        activityMiembrosBinding.setMiembro(miembrosViewModel);
        activityMiembrosBinding.setLifecycleOwner(this);

        MiembroController miembroController = new MiembroController(this, miembrosViewModel, miembroDao);

        TextInputEditText bithdayTextInput = findViewById(R.id.birthday_miembro_field_true);
        Button button = findViewById(R.id.miembro_registrar);
        button.setOnClickListener(miembroController);
        bithdayTextInput.setOnClickListener(miembroController);


        ListView listView = findViewById(R.id.miembros_list);
        MiembroListAdapter miembroListAdapter = new MiembroListAdapter(this, miembroController);
        listView.setAdapter(miembroListAdapter);
        miembrosViewModel.getMiembros().observe(this, miembros -> {
            miembroListAdapter.clear();
            miembroListAdapter.addAll(miembros);
            miembroListAdapter.notifyDataSetChanged();
        });

        createValidations(miembrosViewModel);


        MaterialToolbar toolbar = findViewById(R.id.miembrosToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void createValidations(MiembrosViewModel miembrosViewModel) {
        miembrosViewModel.getNombre().observe(this, value -> this.emptyFieldValidator(value, R.id.nombre_miembro_field, miembrosViewModel.getNombreValid()));
        miembrosViewModel.getFechaDeNacimiento().observe(this, value -> this.emptyFieldValidator(value, R.id.birthday_miembro_field, miembrosViewModel.getFechaDeNacimientoValid()));
        miembrosViewModel.getDireccion().observe(this, value -> this.emptyFieldValidator(value, R.id.direccion_miembro_field, miembrosViewModel.getDireccionValid()));
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