package com.elenajif.ejercicio8;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by DAM on 28/01/2022.
 */
@Entity
@Table(name = "carritos", schema = "ejercicio8", catalog = "")
public class Carrito {
    private int id;
    private Date fechaCreacion;
    private double precioTotal;
    private byte finalizado;
    private int idUsuario;
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

    @Basic
    @Column(name = "id_usuario")
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carrito carrito = (Carrito) o;

        if (id != carrito.id) return false;
        if (Double.compare(carrito.precioTotal, precioTotal) != 0) return false;
        if (finalizado != carrito.finalizado) return false;
        if (idUsuario != carrito.idUsuario) return false;
        if (fechaCreacion != null ? !fechaCreacion.equals(carrito.fechaCreacion) : carrito.fechaCreacion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (fechaCreacion != null ? fechaCreacion.hashCode() : 0);
        temp = Double.doubleToLongBits(precioTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) finalizado;
        result = 31 * result + idUsuario;
        return result;
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
