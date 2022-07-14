package com.example.exercise.repository;

import com.example.exercise.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Modifying
    @Query(value = "INSERT INTO bills (create_date, create_hour, buy_description, buy_observation, bill_id) " +
            "VALUES (NOW(), NOW(), :description, :observation, :billId)", nativeQuery = true)
    void createBill(@Param("description") String description,
                    @Param("observation") String observation);

    @Modifying
    @Query(value = "UPDATE bills b SET b.client_client_id = :clientId WHERE bill_id = :BillId", nativeQuery = true)
    Bill relateBillToCustomer(@Param("clientId") Long clientId, @Param("BillId") Long billId);
}
