package com.example.exercise.service.interfaces;

import com.example.exercise.model.Item;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

public interface IPaymentService {

    Map<String, Object> createNewBillClient(String description, String observation, Long clientId);
    Map<String, Object> addItemsToBill(Long billId, List<Item> item);
    Map<String, Object> doPayment(Long billId, Double amount);
}
