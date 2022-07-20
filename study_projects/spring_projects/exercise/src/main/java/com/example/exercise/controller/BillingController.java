package com.example.exercise.controller;

import com.example.exercise.DTO.ItemDTO;
import com.example.exercise.DTO.PaymentDTO;
import com.example.exercise.model.Item;
import com.example.exercise.service.BillingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/billings")
public class BillingController {

    private final BillingServiceImpl billingService;

    @Autowired
    public BillingController(BillingServiceImpl billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/new-bill")
    public ResponseEntity<Map<String, Object>> newBill(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(billingService.createNewBill(
                paymentDTO.getDescription(),
                paymentDTO.getObservation()),
                HttpStatus.CREATED);
    }

    @PostMapping("/add-items/{bill-id}")
    public ResponseEntity<Map<String, Object>> addItemBill(@RequestBody ItemDTO itemDTO,
                                                           @PathVariable(name = "bill-id") Long billId,
                                                           @RequestParam(name = "product-id") Long productId ){
        return new ResponseEntity<>(billingService.addItemToBill(
                billId,
                new Item(itemDTO.getAmount()),
                productId),
                HttpStatus.OK);
    }

    @PutMapping("/relate-bill")
    public ResponseEntity<Map<String, Object>> relateBillToCustomer(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(billingService.relateBillToCustomer(
                paymentDTO.getClientId(),
                paymentDTO.getBillId()),
                HttpStatus.CREATED);
    }

    @PutMapping("/payment")
    public ResponseEntity<Map<String, Object>> doPayment(@RequestBody PaymentDTO paymentDTO){
        return new ResponseEntity<>(billingService.doPayment(
                paymentDTO.getBillId(),
                paymentDTO.getAmountToPay()),
                HttpStatus.OK);
    }
}
