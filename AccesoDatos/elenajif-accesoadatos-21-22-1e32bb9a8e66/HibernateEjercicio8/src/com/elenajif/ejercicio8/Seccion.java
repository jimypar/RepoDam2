package com.elenajif.ejercicio8;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by DAM on 28/01/2022.
 */
@Entity
@Table(name = "secciones", schema = "ejercicio8", catalog = "")
public class Seccion {
    private int id;
    private String codigo;
    private String nombre;
    private String descripcion;
    private Date fechaCreacion;
    private List<Visita> visitas;
    private List<Producto> productos;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "codigo")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Seccion seccion = (Seccion) o;

        if (id != seccion.id) return false;
        if (codigo != null ? !codigo.equals(seccion.codigo) : seccion.codigo != null) return false;
        if (nombre != null ? !nombre.equals(seccion.nombre) : seccion.nombre != null) return false;
        if (descripcion != null ? !descripcion.equals(seccion.descripcion) : seccion.descripcion != null) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(seccion.fechaCreacion) : seccion.fechaCreacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (codigo != null ? codigo.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "seccion")
    public List<Visita> getVisitas() {
        return visitas;
    }

    public void setVisitas(List<Visita> visitas) {
        this.visitas = visitas;
    }

    @ManyToMany
    @JoinTable(name = "seccion_producto", catalog = "", schema = "ejercicio8", joinColumns = @JoinColumn(name = "id_producto", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_seccion", referencedColumnName = "id", nullable = false))
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
