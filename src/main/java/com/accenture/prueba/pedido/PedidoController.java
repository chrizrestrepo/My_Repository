package com.accenture.prueba.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(path = "/nuevo")
    public void generarPedido(@RequestBody Pedido pedido){
        pedidoService.generarPedido(pedido);
    }

    @PutMapping(path = {"idPedido"})
    public void actualizarOEditarPedido(@PathVariable Long idPedido, Long idProducto, Long idNuevoProducto){
        pedidoService.editarPedido(idPedido, idProducto, idNuevoProducto);
    }


}
