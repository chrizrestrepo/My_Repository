package com.accenture.prueba.cliente;


import com.accenture.prueba.pedido.Pedido;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente {

    @Id
    private Long idCliente;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(length = 150)
    private String direccion;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_pedido")
    private List<Pedido> pedido;

    public Cliente() {
    }

    public Cliente(Long idCliente, String name, String direccion) {
        this.idCliente = idCliente;
        this.nombre = name;
        this.direccion = direccion;
    }

    public Cliente(Long idCliente, String nombre, String direccion, List<Pedido> pedido) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.pedido = pedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
