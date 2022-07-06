package com.example.relations.model.service;

import com.example.relations.model.entity.Customer;
import com.example.relations.model.entity.Product;
import com.example.relations.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service("orderService2")
public class OtherOrderServiceImpl implements IOrderService {

    private final CustomerRepository customerRepository;

    @Autowired
    public OtherOrderServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public void addShoppingCar(Long idCostumer, List<Product> productList){

        Customer customer = customerRepository.findById(idCostumer)
                .orElseThrow(() -> new NoSuchElementException("the person with id: " + idCostumer + "were not found"));

        customer.getProducts().addAll(productList);

        customerRepository.save(customer);
    }
}
