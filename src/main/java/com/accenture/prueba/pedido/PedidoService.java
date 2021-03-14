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
import java.util.List;


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


    public Pedido traerPedidoPorId(Long idPedido){
       return pedidoRepository.findById(idPedido).orElseThrow(() -> new IllegalStateException("el pedido con el id: "+ idPedido + "no existe"));
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


    public void editarPedido(Long idPedido, List<Producto> listaProductos){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();

        if(pedido.getFechaPedido().plusHours(5).isAfter(LocalDateTime.now()) || pedido.getFechaPedido().equals(LocalDateTime.now())){
            if(listaProductos.stream().mapToInt(e -> e.getPrecio()).sum() >= pedido.getTotalFactura()){
                pedido.setListaProductos(listaProductos);
            }else {
                throw new IllegalStateException("el valor del pedido debe ser mayor o igual al anterior de: " + pedido.getTotalFactura());
            }
        }

        Integer totalnuevaFactura = pedido.getListaProductos().stream().mapToInt(e -> e.getPrecio()).sum();

        if(totalnuevaFactura < 100000){
            pedido.setTotalFactura((int) (totalnuevaFactura + totalnuevaFactura*0.19) + pedido.TOTAL_DOMICILIO);
        }else{
            pedido.setTotalFactura((int) (totalnuevaFactura + totalnuevaFactura*0.19));
        }

        }

    public void agregarProductoPedido(Long idPedido, Long idProducto){
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();

        if(pedido.getFechaPedido().plusHours(5).isAfter(LocalDateTime.now()) || pedido.getFechaPedido().equals(LocalDateTime.now())){
            pedido.getListaProductos().add(productoRepository.findById(idProducto).orElseThrow());
        }

        Integer totalnuevaFactura = pedido.getListaProductos().stream().mapToInt(e -> e.getPrecio()).sum();

        if(totalnuevaFactura < 100000){
            pedido.setTotalFactura((int) (totalnuevaFactura + totalnuevaFactura*0.19) + pedido.TOTAL_DOMICILIO);
        }else{
            pedido.setTotalFactura((int) (totalnuevaFactura + totalnuevaFactura*0.19));
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
