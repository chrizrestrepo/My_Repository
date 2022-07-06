package org.cyrestrepo.test.springboot.app.service;

import org.cyrestrepo.test.springboot.app.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    Account save(Account account);
    List<Account> findAll();
    Account findById(Long id);
    int getTotalTransfer(Long bankId);
    BigDecimal getBalance(Long accountId);
    void transfer(Long origin, Long destiny, BigDecimal balance, Long bankId);
    //Account findByPersonName(String personName);
}
