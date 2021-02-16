package com.lania.ejercicio2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.sqlite.db.SupportSQLiteStatement;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.lania.ejercicio2.componentes.editor.EditorActivity;
import com.lania.ejercicio2.componentes.libros.LibroActivity;
import com.lania.ejercicio2.componentes.miembro.MiembrosActivity;
import com.lania.ejercicio2.dominio.AppDatabase;
import com.lania.ejercicio2.dominio.dao.EditorDao;
import com.lania.ejercicio2.dominio.dao.LibroDao;
import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.dominio.entidades.Miembro;
import com.lania.ejercicio2.utils.DatabaseProvider;

import java.util.Locale;
import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.topBar);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);

        appDatabase = DatabaseProvider.getDatabase(this.getApplicationContext());
        SupportSQLiteStatement qry0 = appDatabase.compileStatement("SELECT 1 + 1");
        long result = qry0.simpleQueryForLong();
        Log.d("MainActivity", "test:" + result);
        updateUi();

    }

    private void updateUi() {
        MiembroDao miembroDao = appDatabase.miembroDao();
        LibroDao libroDao = appDatabase.libroDao();
        EditorDao editorDao = appDatabase.editorDao();

        Long libroCount = libroDao.count();
        Long miembroCount = miembroDao.count();
        Long editorCount = editorDao.count();

        TextView libroCounterLabel = findViewById(R.id.libros_counter_label);
        TextView miembroCounterLabel = findViewById(R.id.miembro_counter_label);
        TextView editorCounterLabel = findViewById(R.id.editores_counter_label);

        Resources res = getResources();

        libroCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.libros_registrados_1_s), libroCount));
        miembroCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.miembros_registrados_1_s), miembroCount));
        editorCounterLabel.setText(String.format(Locale.getDefault(), res.getString(R.string.editores_registrados_1_s), editorCount));
    }


    @Override
    protected void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.item_libro:
                intent = new Intent(MainActivity.this, LibroActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_editor:
                intent = new Intent(MainActivity.this, MiembrosActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_miembro:
                intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }

    }
}