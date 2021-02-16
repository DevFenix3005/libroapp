package com.lania.ejercicio2.componentes.miembro;

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
import com.lania.ejercicio2.databinding.MiembroItemBinding;
import com.lania.ejercicio2.dominio.entidades.Miembro;

public class MiembroListAdapter extends ArrayAdapter<Miembro> {

    private final Activity activity;
    private final MiembroController miembroController;

    public MiembroListAdapter(@NonNull MiembrosActivity activity, @NonNull MiembroController miembroController) {
        super(activity, R.layout.miembro_item);
        this.activity = activity;
        this.miembroController = miembroController;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.activity.getLayoutInflater();
        MiembroItemBinding miemrboItemBinding = DataBindingUtil.inflate(inflater, R.layout.miembro_item, parent, false);
        Miembro miembro = this.getItem(position);
        MiembroItemViewModel miembroItemViewModel = new MiembroItemViewModel(miembro);
        miemrboItemBinding.setMiembro(miembroItemViewModel);
        View view = miemrboItemBinding.getRoot();
        AppCompatImageButton imageButton = view.findViewById(R.id.miembro_borrar_button);
        imageButton.setTag(miembro);
        imageButton.setOnClickListener(this.miembroController);
        return view;
    }
}
