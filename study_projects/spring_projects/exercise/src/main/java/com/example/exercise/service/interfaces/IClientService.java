package com.example.exercise.service.interfaces;

import com.example.exercise.model.Client;

import java.util.Map;

public interface IClientService {

    Client findClientById(Long id);
    Map<String, Object> createClient(Client client);
}
