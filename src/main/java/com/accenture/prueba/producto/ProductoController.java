package com.accenture.prueba.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/producto_Controller")
public class ProductoController {


    private ProductoService service;

    @Autowired
    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public List<com.accenture.prueba.producto.Producto> traerListaClientes(){
        return service.traerProductos();
    }

    @GetMapping(path = "/pById")
    public com.accenture.prueba.producto.Producto traerClientePorId(@RequestParam(name = "id") Long id){
        return service.traerProductoPorId(id);
    }

    @GetMapping(path = "/pListByName")
    public List<com.accenture.prueba.producto.Producto> traerListaClientePorNombre(@RequestParam(name = "nombre") String nombre){
        return service.traerProductoPorNombre(nombre);
    }

    @PostMapping(path = "/crearP")
    public void crearNuevoProducto(@RequestBody Producto producto){
        service.crearProducto(producto);
    }

    @DeleteMapping(path = "{idProducto}")
    public void eliminarProducto(@PathVariable Long idProducto){
        service.eliminarProducto(idProducto);
    }

    @PostMapping(path = {"productoId"})
    public void actualizarPrecio(
            @PathVariable Long productoId,
            @RequestParam String nombre,
            @RequestParam Integer precio){
        service.actualizarProducto(productoId, nombre, precio);
    }
}
