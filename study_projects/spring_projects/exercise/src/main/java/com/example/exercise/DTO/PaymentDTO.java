package com.example.exercise.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {

    public PaymentDTO(String description, String observation, Long clientId) {
        this.description = description;
        this.observation = observation;
        this.clientId = clientId;
    }

    public PaymentDTO(Long billId, List<ItemDTO> itemDTOS) {
        this.billId = billId;
        this.itemDTOS = itemDTOS;
    }

    private String description;
    private String observation;
    private Long clientId;
    private Long billId;
    private List<ItemDTO> itemDTOS;
}
