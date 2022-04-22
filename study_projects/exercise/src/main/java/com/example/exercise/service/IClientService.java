package com.example.exercise.service;

import com.example.exercise.model.Client;

import java.util.Map;

public interface IClientService {

    Map<String, Object> findClientById(Long id);
    Map<String, Object> createClient(Client client);
}
