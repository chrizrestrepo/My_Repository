package com.accenture.prueba.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping(path = "/")
    public List<Cliente> traerListaClientes(){
        return service.traerClientes();
    }

    @GetMapping(path = "/cById")
    public Cliente traerClientePorId(@RequestParam(name = "clienteId") Long clienteId){
        return service.traerclientePorId(clienteId);
    }

    @PostMapping(path = "/crearC")
    public void crearNuevoCliente(@RequestBody Cliente cliente){
        service.crearCliente(cliente);
    }

    @DeleteMapping(path = "{clienteId}")
    public void eliminarCliente(@PathVariable Long clienteId){
        service.eliminarCliente(clienteId);
    }

    @PostMapping(path = {"clienteId"})
    public void actualizarCliente(
            @PathVariable Long clienteId,
            @RequestParam String nombre,
            @RequestParam(required = false) String direccion){
        service.actualizarCliente(clienteId, nombre, direccion);
    }

}