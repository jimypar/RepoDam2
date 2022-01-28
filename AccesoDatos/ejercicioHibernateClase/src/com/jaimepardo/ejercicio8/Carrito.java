package com.jaimepardo.ejercicio8;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "carritos", schema = "ejercicio8", catalog = "")
public class Carrito {
    private int id;
    private Date fechaCreacion;
    private double precioTotal;
    private byte finalizado;
    private Usuario usuario;
    private List<DetalleCarrito> detalles;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "precio_total")
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Basic
    @Column(name = "finalizado")
    public byte getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(byte finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carrito carrito = (Carrito) o;
        return id == carrito.id &&
                Double.compare(carrito.precioTotal, precioTotal) == 0 &&
                finalizado == carrito.finalizado &&
                Objects.equals(fechaCreacion, carrito.fechaCreacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaCreacion, precioTotal, finalizado);
    }

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToMany(mappedBy = "carrito")
    public List<DetalleCarrito> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCarrito> detalles) {
        this.detalles = detalles;
    }
}
