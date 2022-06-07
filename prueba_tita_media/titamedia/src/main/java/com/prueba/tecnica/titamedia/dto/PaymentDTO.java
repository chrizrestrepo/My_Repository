package com.prueba.tecnica.titamedia.dto;

import lombok.Data;

@Data
public class PaymentDTO {

    private Long userId;
    private String bankName;
    private String payReference;
    private Long value;

}
