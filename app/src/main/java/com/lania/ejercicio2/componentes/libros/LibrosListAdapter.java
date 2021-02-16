package com.lania.ejercicio2.componentes.libros;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingUtil;

import com.lania.ejercicio2.R;
import com.lania.ejercicio2.databinding.LibroItemBinding;
import com.lania.ejercicio2.dominio.entidades.Libro;

public class LibrosListAdapter extends ArrayAdapter<Libro> {

    private final Activity context;
    private final LibroController libroController;


    public LibrosListAdapter(@NonNull Activity context, LibroController libroController) {
        super(context, R.layout.libro_item);
        this.context = context;
        this.libroController = libroController;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        LibroItemBinding libroItemBinding = DataBindingUtil.inflate(inflater, R.layout.libro_item, parent, false);
        Libro libro = this.getItem(position);
        LibroItemViewModel libroItemViewModel = new LibroItemViewModel();
        libroItemViewModel.getData().setValue(libro);
        libroItemBinding.setLibro(libroItemViewModel);

        View view = libroItemBinding.getRoot();

        AppCompatImageButton button = view.findViewById(R.id.libro_borrar_button);
        button.setTag(libro);
        button.setOnClickListener(libroController);

        return view;
    }
}
