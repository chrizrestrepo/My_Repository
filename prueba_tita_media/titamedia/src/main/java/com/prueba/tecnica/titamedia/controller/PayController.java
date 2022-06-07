package com.prueba.tecnica.titamedia.controller;

import com.prueba.tecnica.titamedia.dto.PaymentDTO;
import com.prueba.tecnica.titamedia.model.entity.Debt;
import com.prueba.tecnica.titamedia.model.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController("/api/v1/payment")
public class PayController {

    private final IPayService iPayService;

    @Autowired
    public PayController(IPayService iPayService) {
        this.iPayService = iPayService;
    }

    @GetMapping(name = "/banks")
    public ResponseEntity<List<String>> getBanksByUserId(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(iPayService
                .getBanksByUserId(paymentDTO.getUserId()), HttpStatus.FOUND);
    }

    @GetMapping(name = "/debts")
    public ResponseEntity<List<Debt>> getDebtsByUserIdAndBank(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(iPayService
                .getDebtsByUserIdAndBank(paymentDTO.getUserId(), paymentDTO.getBankName()), HttpStatus.FOUND);
    }

    @PostMapping(name = "/payDebt")
    public ResponseEntity<Map<String, Object>> payDebt(@RequestBody PaymentDTO paymentDTO){
        iPayService.payDebt(paymentDTO.getUserId(), paymentDTO.getBankName(), paymentDTO.getPayReference(), paymentDTO.getValue());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "pay succesful");
        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }


}
