package com.lania.ejercicio2.dominio.entidades;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "editores")
public class Editor {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String nombre;
    private String direccion;

    public Editor() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Editor)) return false;
        final Editor other = (Editor) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nombre = this.getNombre();
        final Object other$nombre = other.getNombre();
        if (this$nombre == null ? other$nombre != null : !this$nombre.equals(other$nombre))
            return false;
        final Object this$direccion = this.getDireccion();
        final Object other$direccion = other.getDireccion();
        if (this$direccion == null ? other$direccion != null : !this$direccion.equals(other$direccion))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Editor;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nombre = this.getNombre();
        result = result * PRIME + ($nombre == null ? 43 : $nombre.hashCode());
        final Object $direccion = this.getDireccion();
        result = result * PRIME + ($direccion == null ? 43 : $direccion.hashCode());
        return result;
    }

    public String toString() {
        return "Editor(id=" + this.getId() + ", nombre=" + this.getNombre() + ", direccion=" + this.getDireccion() + ")";
    }
}
