package com.lania.ejercicio2.componentes.miembro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lania.ejercicio2.dominio.dao.MiembroDao;
import com.lania.ejercicio2.dominio.entidades.Miembro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MiembrosViewModel extends ViewModel {

    private MiembroDao miembroDao;

    private final MutableLiveData<String> nombre = new MutableLiveData<>();
    private final MutableLiveData<String> fechaDeNacimiento = new MutableLiveData<>();
    private final MutableLiveData<String> direccion = new MutableLiveData<>();
    private MutableLiveData<List<Miembro>> miembros;

    private final MutableLiveData<Boolean> nombreValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> fechaDeNacimientoValid = new MutableLiveData<>(Boolean.FALSE);
    private final MutableLiveData<Boolean> direccionValid = new MutableLiveData<>(Boolean.FALSE);

    public MutableLiveData<List<Miembro>> getMiembros() {

        if (this.miembros == null) {
            this.miembros = new MutableLiveData<>();
            List<Miembro> lista = miembroDao.getAllMiembros();
            miembros.setValue(lista);
        }

        return miembros;
    }

    public Miembro createMiembroFromViewModel() {
        String nombreMiembro = this.nombre.getValue();
        String fechaDeNacimientoMiembro = this.fechaDeNacimiento.getValue() + "T00:00:00";
        String direccionMiembro = this.direccion.getValue();

        Miembro miembro = new Miembro();
        miembro.setDireccion(direccionMiembro);
        miembro.setFechaDeRegistro(LocalDateTime.now());
        miembro.setFechaDeNacimiento(LocalDateTime.parse(fechaDeNacimientoMiembro, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        miembro.setNombre(nombreMiembro);

        return miembro;
    }


    public void clean() {
        nombre.setValue("");
        fechaDeNacimiento.setValue("");
        direccion.setValue("");
    }
}
