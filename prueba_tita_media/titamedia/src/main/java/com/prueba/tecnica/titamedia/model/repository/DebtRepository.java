package com.prueba.tecnica.titamedia.model.repository;

import com.prueba.tecnica.titamedia.model.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DebtRepository extends JpaRepository<Debt, Long> {
}
