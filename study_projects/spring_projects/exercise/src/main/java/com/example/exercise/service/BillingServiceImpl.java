package com.example.exercise.service;

import com.example.exercise.model.Item;
import com.example.exercise.repository.BillRepository;
import com.example.exercise.service.interfaces.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class BillingServiceImpl implements IBillingService {

    private final BillRepository billRepository;
    private final ProductServiceImpl productService;
    private Map<String, Object> response;

    @Autowired
    public BillingServiceImpl(BillRepository billRepository, ProductServiceImpl productService) {
        this.billRepository = billRepository;
        this.productService = productService;
    }

    @Override
    @Transactional
    public Map<String, Object> createNewBill(String description, String observation) {
        response = new LinkedHashMap<>();
        billRepository.createBill(description, observation, 100L);
        response.put("message", "new bill create with the next description: ".concat(description));
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> addItemToBill(Long billId, Item item, Long productId){
        response = new LinkedHashMap<>();

        try{
            billRepository.findById(billId)
                    .map(bill -> {
                        item.setProduct(productService.getProductById(productId));
                        bill.addItem(item);
                        bill.setTotalBill(bill.calculateTotalBill());
                        return bill;
                    })
                    .map(billRepository::save)
                    .orElseThrow(() -> new NoSuchElementException("the bill with id: "
                            .concat(billId.toString())
                            .concat(" not found")));

            response.put("Bill_Id", billId.toString());
            response.put("Added Item", item);
            response.put("message", "the item were save succesfully into the bill with id: ".concat(billId.toString()));
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> relateBillToCustomer(Long clientId, Long billId) {
        response = new LinkedHashMap<>();
        try{
            billRepository.relateBillToCustomer(clientId, billId);
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }
        response.put("message", "the Bill was related to Client with id: ".concat(clientId.toString()));
        return response;
    }

    @Override
    @Transactional
    public Map<String, Object> doPayment(Long billId, Double amount) {
        response = new LinkedHashMap<>();
        try{
            billRepository.findById(billId)
                    .map(bill -> {
                        Double substract = (bill.getTotalBill() - amount);
                        if(substract < 0){
                            throw new IllegalArgumentException("the value of this bill can't be less a 0 - try with another value");
                        }
                        bill.setTotalBill(substract);
                        return bill;
                    })
                    .map(billRepository::save)
                    .orElseThrow(() -> new NoSuchElementException("the bill with id: "
                            .concat(billId.toString())
                            .concat(" not found")));

            response.put("message", "payment successful");
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }
        return response;
    }
}
