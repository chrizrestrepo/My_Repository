package com.accenture.prueba.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ProductoService {

    private ProductoRepository repository;

    @Autowired
    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<com.accenture.prueba.producto.Producto> traerProductos(){
        return repository.findAll();
    }

    public com.accenture.prueba.producto.Producto traerProductoPorId(Long id){
        return repository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("el producto con el id: "+ id + "no existe"));
    }

    public List<Producto> traerProductoPorNombre(String nombre){
        return repository.findByNombreProducto(nombre);
    }

    public void crearProducto(Producto producto){
        if(repository.findById(producto.getIdProducto()).isPresent()){
            throw new IllegalStateException("el cliente con el id: " + producto.getIdProducto() + "ya existe");
        }
        repository.save(producto);
    }

    public void eliminarProducto(Long idProducto){
        repository.deleteById(idProducto);
    }

    public void actualizarProducto(Long id, String nombreProducto, Integer precio){
        Producto productoActualizar = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("el cliente con el id: "+ id + "no existe"));

        if(nombreProducto != null && nombreProducto.length() > 0 && !Objects.equals(productoActualizar.getNombreProducto(), nombreProducto)){
            productoActualizar.setNombreProducto(nombreProducto);
        }

        if(precio != null && precio > 0 && productoActualizar.getPrecio() != precio){
            productoActualizar.setPrecio(precio);
        }
    }
}
