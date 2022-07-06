package com.example.exercise.service;

import com.example.exercise.model.Bill;
import com.example.exercise.model.Item;
import com.example.exercise.repository.BillRepository;
import com.example.exercise.repository.ClientRepository;
import com.example.exercise.service.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements IPaymentService {

    private final ClientRepository clientRepository;
    private final BillRepository billRepository;
    private final ClientServiceImpl clientService;

    @Autowired
    public PaymentServiceImpl(ClientRepository clientRepository, BillRepository billRepository, ClientServiceImpl clientService) {
        this.clientRepository = clientRepository;
        this.billRepository = billRepository;
        this.clientService = clientService;
    }


    @Override
    public Map<String, Object> createNewBillClient(String description, String observation, Long clientId) {
        Map<String, Object> response = new LinkedHashMap<>();

        try{
            billRepository.save(new Bill(description, observation, clientService.findClientById(clientId)));
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }

        response.put("message", "new bill create with the next description: ".concat(description));
        return response;
    }

    @Override
    public Map<String, Object> addItemsToBill(Long billId, List<Item> item){
        Map<String, Object> response = new LinkedHashMap<>();

        try{
            billRepository.findById(billId)
                    .map(bill -> {
                        bill.addItem(item);
                        bill.setTotalBill(bill.calculateTotalBill());
                        return bill;
                    })
                    .map(billRepository::save)
                    .orElseThrow(() -> new NoSuchElementException("the bill with id: "
                            .concat(billId.toString())
                            .concat(" not found")));
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }
        response.put("message", "the items were save succesfully into: ".concat(billId.toString()));
        return response;
    }

    @Override
    public Map<String, Object> doPayment(Long billId, Double amount) {
        Map<String, Object> response = new LinkedHashMap<>();
        try{
            billRepository.findById(billId)
                    .map(bill -> {
                        Double substract = (bill.getTotalBill() - amount);
                        if(substract < 0){
                            throw new IllegalArgumentException("the value of this bill can't be less a 0 - try with another value");
                        }
                        bill.setTotalBill(bill.getTotalBill() - amount);
                        return bill;
                    })
                    .map(billRepository::save)
                    .orElseThrow(() -> new NoSuchElementException("the bill with id: "
                            .concat(billId.toString())
                            .concat(" not found")));
        }catch(DataAccessException exception){
            throw new RuntimeException("there was an error trying to insert the record");
        }
        response.put("message", "payment successful");
        return response;
    }
}
