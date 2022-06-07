package com.prueba.tecnica.titamedia.model.repository;

import com.prueba.tecnica.titamedia.model.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
