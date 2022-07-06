package com.example.exercise.controller;

import com.example.exercise.DTO.PaymentDTO;
import com.example.exercise.model.Item;
import com.example.exercise.service.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/new-bill")
    public ResponseEntity<Map<String, Object>> newBill(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(paymentService.createNewBillClient(
                paymentDTO.getDescription(),
                paymentDTO.getObservation(),
                paymentDTO.getClientId()),
                HttpStatus.CREATED);
    }

    @PostMapping("/add-items")
    public ResponseEntity<Map<String, Object>> addItemsBill(@RequestParam Long billId, @RequestBody List<Item> items){
        return new ResponseEntity<>(paymentService.addItemsToBill(billId, items), HttpStatus.OK);
    }
}
