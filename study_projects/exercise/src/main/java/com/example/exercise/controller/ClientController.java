package com.example.exercise.controller;


import com.example.exercise.model.Client;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @PostMapping(path = "")
    public ResponseEntity<?> createClient(@Valid @RequestBody Client client, BindingResult result){

        Map<String, Object> response = new LinkedHashMap<>();

        if(result.hasErrors()){
            List<String> listErros = result.getFieldErrors()
                    .stream()
                    .map(error -> "el campo " + error.getField() + " " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", listErros);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        response.put("message", "registro insertado con exito");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
