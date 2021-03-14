package com.accenture.prueba.pedido;

import com.accenture.prueba.producto.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public Pedido traerPedido(@RequestParam Long idPedido){
        return pedidoService.traerPedidoPorId(idPedido);
    }

    @PostMapping(path = "/nuevo")
    public void generarPedido(@RequestBody Pedido pedido){
        pedidoService.generarPedido(pedido);
    }

    @PutMapping(path = {"idPedido"})
    public void agregarItemPedido(@PathVariable("idPedido") Long idPedido, @RequestParam Long idProducto){
        pedidoService.agregarProductoPedido(idPedido, idProducto);
    }

    @PostMapping(path = {"idPedido"})
    public void editarPedido(@PathVariable("idPedido") Long idPedido, @RequestBody List<Producto> listaProductos){
        pedidoService.editarPedido(idPedido, listaProductos);
    }

    @DeleteMapping(path = {"idPedido"})
    public void eliminarpedido(@PathVariable("idPedido") Long idPedido){
        pedidoService.eliminarPedido(idPedido);
    }


}
