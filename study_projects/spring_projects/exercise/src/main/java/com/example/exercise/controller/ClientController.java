package com.example.exercise.controller;


import com.example.exercise.DTO.ClientDTO;
import com.example.exercise.model.Client;
import com.example.exercise.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/get-client/{client-id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable(name = "client-id") Long clientId){
        return new ResponseEntity<>(ClientDTO.toDTO(clientService.findClientById(clientId)), HttpStatus.FOUND);
    }

    @PostMapping(path = "/new-client")
    public ResponseEntity<Map<String, Object>> createClient(@Valid @RequestBody Client client){
        return new ResponseEntity<>(clientService.createClient(client), HttpStatus.CREATED);
    }
}
