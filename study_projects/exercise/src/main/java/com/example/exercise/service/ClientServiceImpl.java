package com.example.exercise.service;

import com.example.exercise.model.Client;
import com.example.exercise.repository.ClientRepository;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ClientServiceImpl implements IClientService{

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Map<String, Object> findClientById(Long id) {

        Map<String, Object> response = new LinkedHashMap<>();

        Client client = clientRepository.findById(id).orElse(null);
        if(client == null){
            response.put("message", "el cliente con el id: " + id + " no existe");
            response.put("Http:Status", 400);
            return response;
        }
        return response;
    }

    @Override
    public Map<String, Object> createClient(Client client){

        Map<String, Object> response = new LinkedHashMap<>();

        try{
            clientRepository.save(client);
        }catch(DataAccessException exception){
            response.put("message", "hubo un error al insertar el cliente");
            response.put("httpStatus", exception.getMessage() + " " + exception.getMostSpecificCause().getMessage());
            return response;
        }

        response.put("message", "el cliente fue insertado con exito");

        return response;
    }
}
