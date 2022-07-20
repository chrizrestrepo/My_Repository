package com.example.exercise.service.interfaces;

import com.example.exercise.model.Item;

import java.util.Map;

public interface IBillingService {

    Map<String, Object> createNewBill(String description, String observation);
    Map<String, Object> addItemToBill(Long billId, Item item, Long productId);
    Map<String, Object> relateBillToCustomer(Long clientId, Long billId);
    Map<String, Object> doPayment(Long billId, Double amount);
}
