package com.jaimepardo.ejercicio8;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

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
        return id == seccion.id &&
                Objects.equals(codigo, seccion.codigo) &&
                Objects.equals(nombre, seccion.nombre) &&
                Objects.equals(descripcion, seccion.descripcion) &&
                Objects.equals(fechaCreacion, seccion.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombre, descripcion, fechaCreacion);
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
