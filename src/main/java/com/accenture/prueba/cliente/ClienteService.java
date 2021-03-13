package com.accenture.prueba.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class ClienteService {

    private final ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> traerClientes(){
        return repository.findAll();
    }

    public Cliente traerclientePorId(Long clienteId){
        return repository.findById(clienteId).orElseThrow(() ->
                new IllegalArgumentException("el cliente con el id: "+ clienteId + "no existe"));
    }

    public void crearCliente(Cliente cliente){
        if(repository.findById(cliente.getIdCliente()).isPresent()){
            throw new IllegalStateException("el cliente con el id: " + cliente.getIdCliente() + "ya existe");
        }
        repository.save(cliente);
    }

    public void eliminarCliente(Long idCliente){
        repository.deleteById(idCliente);
    }

    public void actualizarCliente(Long idCliente, String nombre, String direccion){
        Cliente clienteActualizar = repository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("el cliente con el id: "+ idCliente + "no existe"));

        if(nombre != null && nombre.length() > 0 && !Objects.equals(clienteActualizar.getNombre(), nombre)){
            clienteActualizar.setNombre(nombre);
        }

        if(direccion != null && direccion.length() > 0 && !Objects.equals(clienteActualizar.getDireccion(), direccion)){
            clienteActualizar.setDireccion(direccion);
        }
    }
}
