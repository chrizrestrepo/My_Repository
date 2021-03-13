package com.accenture.prueba.pedido;

import com.accenture.prueba.cliente.Cliente;
import com.accenture.prueba.cliente.ClienteRepository;
import com.accenture.prueba.producto.Producto;
import com.accenture.prueba.producto.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Iterator;


@Service
@Transactional
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void generarPedido(Pedido pedido){
        Integer total = pedido.getListaProductos().stream().mapToInt(e -> e.getPrecio()).sum();

        if(total < 100000){
            pedido.setTotalFactura((int) (total + total*0.19) + pedido.TOTAL_DOMICILIO);
        }else{
            pedido.setTotalFactura((int) (total + total*0.19));
        }

        Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).orElseThrow();
        cliente.getPedido().add(pedido);

        pedido.setFechaPedido(LocalDateTime.now());

        pedidoRepository.save(pedido);
    }

    public void editarPedido(Long idPedido, Long idProducto, Long idNuevoProducto){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        Iterator<Producto> iteratorPedido = pedido.getListaProductos().iterator();

        if(pedido.getFechaPedido().plusHours(5).isAfter(LocalDateTime.now()) || pedido.getFechaPedido().equals(LocalDateTime.now())){
            while (iteratorPedido.hasNext()){
                if(iteratorPedido.next().getIdProducto() == idProducto){
                    pedido.getListaProductos().remove(productoRepository.findById(idProducto));
                    pedido.getListaProductos().add(productoRepository.findById(idNuevoProducto).orElseThrow());
                    break;
                }
            }
        }else{
            throw new IllegalStateException("solo se pueden editar los pedidos hasta 5 horas despues de realizarlo");
        }
    }

    public void eliminarPedido(Long idPedido){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        if(pedido.getFechaPedido().plusHours(12).isAfter(LocalDateTime.now()) || pedido.getFechaPedido().equals(LocalDateTime.now())){
            pedidoRepository.deleteById(idPedido);
        }else{
            Integer totalPedido = pedido.getListaProductos().stream().mapToInt(e -> e.getPrecio()).sum();
            int facturaPedido = (int) (totalPedido*0.10);

            Cliente cliente = clienteRepository.findById(pedido.getIdCliente()).orElseThrow();
            
            pedidoRepository.deleteById(idPedido);

            cliente.getPedido().add(new Pedido(Collections.emptyList(), facturaPedido));
        }
    }

}
