package com.lania.ejercicio2.componentes.miembro;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.lania.ejercicio2.R;
import com.lania.ejercicio2.dialogos.DatePickerFragment;
import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.dominio.entidades.Miembro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MiembroController implements View.OnClickListener {

    @NonNull
    private final MiembrosActivity activity;

    @NonNull
    private final MiembrosViewModel miembrosViewModel;

    @NonNull
    private final MiembroDao miembroDao;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.miembro_registrar:
                registerMiembro();
                break;
            case R.id.birthday_miembro_field_true:
                showDatePickerDialog();
                break;
            case R.id.miembro_borrar_button:
                deleteMiembro(v);

        }
    }

    private void deleteMiembro(View view) {
        Miembro miembro = (Miembro) view.getTag();
        this.miembroDao.deleteMiembro(miembro);

        MutableLiveData<List<Miembro>> mutableMiembrosList = this.miembrosViewModel.getMiembros();
        List<Miembro> lista = mutableMiembrosList.getValue();
        if (lista != null) {
            lista.remove(miembro);
            mutableMiembrosList.setValue(lista);
        }

    }

    private void registerMiembro() {
        Miembro miembro = miembrosViewModel.createMiembroFromViewModel();
        Long newId = miembroDao.insertOne(miembro);
        if (newId != null) {
            miembro.setId(newId);

            MutableLiveData<List<Miembro>> mutableMiembrosList = this.miembrosViewModel.getMiembros();
            List<Miembro> lista = mutableMiembrosList.getValue();
            if (lista != null) {

                List<Miembro> newLista = new ArrayList<>(lista);
                newLista.add(miembro);
                mutableMiembrosList.setValue(newLista);

                String mensaje = String.format(Locale.getDefault(), "El miembro '%s' fue creado y se le asigno el folio %d", miembro.getNombre(), newId);
                Toast.makeText(activity, mensaje, Toast.LENGTH_LONG).show();
                miembrosViewModel.clean();
            }
        }

    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment
                .newInstance((view, year, month, dayOfMonth) ->
                        miembrosViewModel.getFechaDeNacimiento().setValue(String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth)));
        newFragment.show(activity.getSupportFragmentManager(), "datePicker");
    }


}
