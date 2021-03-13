package com.accenture.prueba.pedido;

import com.accenture.prueba.producto.Producto;
import com.accenture.prueba.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;


@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    public void generarPedido(Pedido pedido){
        Integer total = pedido.getListaProductos().stream().mapToInt(e -> e.getPrecio()).sum();

        if(total < 100000){
            pedido.setTotalFactura((int) (total + total*0.19) + pedido.TOTAL_DOMICILIO);
        }else{
            pedido.setTotalFactura((int) (total + total*0.19));
        }

        pedido.setFechaPedido(LocalDateTime.now());
        pedidoRepository.save(pedido);
    }

    public void editarPedido(Long idPedido, Long idProducto, Long idNuevoProducto){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        Iterator<Producto> iteratorPedido = pedido.getListaProductos().iterator();

        if(pedido.getFechaPedido())

        while (iteratorPedido.hasNext()){
            if(iteratorPedido.next().getIdProducto() == idProducto){
                pedido.getListaProductos().remove(productoRepository.findById(idProducto));
                pedido.getListaProductos().add(productoRepository.findById(idNuevoProducto).orElseThrow());
                break;
            }
        }




    }

}
