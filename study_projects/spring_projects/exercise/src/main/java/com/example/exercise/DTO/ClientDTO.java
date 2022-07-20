package com.example.exercise.DTO;

import com.example.exercise.model.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO {

    public ClientDTO(Long clientId, String identification, String firstName, String lastName, String email) {
        this.clientId = clientId;
        this.identification = identification;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private Long clientId;
    private String identification;
    private String firstName;
    private String lastName;
    private String email;
    private List<Object> bills = new ArrayList<>();

    public static ClientDTO toDTO(Client client){
        ClientDTO dto = new ClientDTO(client.getClientId(),
                client.getIdentification(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail());

        dto.getBills().addAll(client.getBills()
                .stream()
                .map(BillDTO::toDto)
                .collect(Collectors.toList()));

        return dto;
    }
}
