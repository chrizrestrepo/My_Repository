package com.prueba.tecnica.titamedia.model.repository;

import com.prueba.tecnica.titamedia.model.entity.Bank;
import com.prueba.tecnica.titamedia.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("select user.bank.bank_name bank_name from users user where bank_name = : userId")
    List<Bank> getBanksByUserId(Long userId);
}
