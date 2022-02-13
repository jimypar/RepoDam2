package com.elenajif.ejercicio8;

import javax.persistence.*;

/**
 * Created by DAM on 28/01/2022.
 */
@Entity
@Table(name = "carrito_producto", schema = "ejercicio8", catalog = "")
public class DetalleCarrito {
    private int id;
    private int cantidad;
    private double precio;
    private Producto producto;
    private Carrito carrito;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DetalleCarrito that = (DetalleCarrito) o;

        if (id != that.id) return false;
        if (cantidad != that.cantidad) return false;
        if (Double.compare(that.precio, precio) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + cantidad;
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
