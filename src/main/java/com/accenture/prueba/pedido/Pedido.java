package com.accenture.prueba.pedido;

import com.accenture.prueba.producto.Producto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Pedido {

    public final int TOTAL_DOMICILIO = 10000;

    @Id
    @SequenceGenerator(
            name = "pedido",
            sequenceName = "pedido_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pedido_sequence")
    private Long idPedido;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_producto")
    private List<Producto> listaProductos;
    @Column
    private Long idCliente;
    private Integer totalFactura;
    private LocalDateTime fechaPedido;

    public Pedido() {
    }

    public Pedido(List<Producto> listaProductos, Long idCliente) {
        this.listaProductos = listaProductos;
        this.idCliente = idCliente;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Integer totalFactura) {
        this.totalFactura = totalFactura;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", listaProductos=" + listaProductos +
                ", idCliente=" + idCliente +
                '}';
    }
}
