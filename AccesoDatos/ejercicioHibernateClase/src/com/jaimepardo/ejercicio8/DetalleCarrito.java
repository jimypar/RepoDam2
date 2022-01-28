package com.jaimepardo.ejercicio8;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "carrito_producto", schema = "ejercicio8", catalog = "")
public class DetalleCarrito {
    private int cantidad;
    private double precio;
    private int id;
    private Producto producto;
    private Carrito carrito;

    @Basic
    @Column(name = "cantidad")
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleCarrito that = (DetalleCarrito) o;
        return cantidad == that.cantidad &&
                Double.compare(that.precio, precio) == 0 &&
                id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad, precio, id);
    }

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id", nullable = false)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @ManyToOne
    @JoinColumn(name = "id_carrito", referencedColumnName = "id", nullable = false)
    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
}
