package com.example.relations.controller;

import com.example.relations.model.entity.Customer;
import com.example.relations.model.entity.Product;
import com.example.relations.model.repository.CustomerRepository;
import com.example.relations.model.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final CustomerRepository customerRepository;
    @Qualifier("orderService2")
    private final IOrderService orderService;

    @Autowired
    public OrderController(CustomerRepository customerRepository, IOrderService orderService) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
    }

    @GetMapping
    public List<Customer> getAllcustomers(){
        return customerRepository.findAll();
    }

    @PutMapping(path = "/client/{idCustomer}/wishlist")
    public void addItemsShoppingCar(@PathVariable(name = "idCustomer") Long idCustomer, @RequestBody List<Product> wishList){
            orderService.addShoppingCar(idCustomer, wishList);
    }
}
