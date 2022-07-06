package com.example.relations.model.service;

import com.example.relations.model.entity.Customer;
import com.example.relations.model.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderService {

    void addShoppingCar(Long idCostumer, List<Product> productList);

    //List<Customer> getCustomersWithOrder();
    //List<Customer> getCustomerByNameAndLastName(String name, String lastName);
}
