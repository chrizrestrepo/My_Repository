package com.example.relations.model.repository;

import com.example.relations.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //@Query("SELECT c FROM Customer c JOIN products p")
    //@Query(value = "select p from products p inner join customers c on c.id = p.fk_costumer", nativeQuery = true)
    //List<Customer> getCustomerJoinPedido();

    //@Query("FROM Customer WHERE firstName =: name AND lastName =: lastName")
    //List<Customer> getCustomerByNameAndLastName(@Param(value = "name") String firstname, @Param(value = "lastName") String lastName);

}
