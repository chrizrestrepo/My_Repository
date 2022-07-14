package com.example.exercise.service;

import com.example.exercise.model.Client;
import com.example.exercise.repository.ClientRepository;
import com.example.exercise.service.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("the client with id: "
                        .concat(id.toString())
                        .concat(" not found")));
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public Map<String, Object> createClient(Client client){

        Map<String, Object> response = new LinkedHashMap<>();
        boolean exist = clientRepository.findAll()
                .stream()
                .anyMatch(person -> person.getIdentification().equals(client.getIdentification()));

        try{
            if(exist){
                throw new IllegalArgumentException("the client with identification: "
                        .concat(client.getIdentification())
                        .concat(" already exist"));
            }
            response.put("data", clientRepository.save(client));
            response.put("message", "the record was entered successfully");
        }catch(DataAccessException exception){
            response.put("message", "there was an error trying to insert the client");
            response.put("detail", exception.getMessage());
            return response;
        }
        return response;
    }

}
