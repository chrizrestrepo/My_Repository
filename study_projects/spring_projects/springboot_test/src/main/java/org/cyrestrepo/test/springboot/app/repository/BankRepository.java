package org.cyrestrepo.test.springboot.app.repository;

import org.cyrestrepo.test.springboot.app.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Long> {
    //List<Bank> findAll();
    //Optional<Bank> findById(Long id);
    //void update(Bank bank);
}
