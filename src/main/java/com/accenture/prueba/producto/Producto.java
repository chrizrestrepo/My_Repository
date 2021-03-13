package com.accenture.prueba.producto;

import javax.persistence.*;

@Entity
public class Producto {

    @Id
    @SequenceGenerator(
            name= "producto",
            sequenceName = "producto_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "producto_sequence")
    private Long idProducto;
    @Column(name = "nombre_producto", nullable = false, unique = true)
    private String nombreProducto;
    @Column(name = "precio", nullable = false)
    private Integer precio;

    public Producto() {
    }

    public Producto(String nombreProducto, Integer precio) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precio=" + precio +
                '}';
    }
}
