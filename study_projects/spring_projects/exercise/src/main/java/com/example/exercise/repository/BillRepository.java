package com.example.exercise.repository;

import com.example.exercise.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Long> {

    @Query("insert into bills (client_client_id, create_date, create_hour, buy_description, buy_observation, total_bill, bill_id) values (?, ?, ?, ?, ?, ?, ?)")
    void save();
}
