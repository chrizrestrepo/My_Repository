package org.cyrestrepo.test.springboot.app.service;

import lombok.RequiredArgsConstructor;
import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.model.Bank;
import org.cyrestrepo.test.springboot.app.repository.AccountRepository;
import org.cyrestrepo.test.springboot.app.repository.BankRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service("accountService")
public class AccountServiceImpl implements IAccountService{

    private final AccountRepository accountRepository;
    private final BankRepository bankRepository;


    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }

    /**
     * la anoptacion @Transactional sirve para envolver cada transsacion de manera unica, por lo que de esta manera le indicamos a
     * spring cuando se hara un commit o no de algo, mediando el parametro readOnly, por lo que en el caso de que vayamos hacer una
     * transaccion tal como guardar o actualizar, es importante tener en cuenta que el parametro  readOnly es false por defecto,
     * adcional que la anotacion puede usarse a nivel de metodo o clase
     */
    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("the account with id: ".concat(String.valueOf(id)).concat(" does´t exist")));
    }

    @Override
    @Transactional(readOnly = true)
    public int getTotalTransfer(Long bankId) {
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new NoSuchElementException("the bank with id: ".concat(String.valueOf(bankId)).concat(" does´t exist")));
        return bank.getTotalTransfer();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new NoSuchElementException("the account with id: ".concat(String.valueOf(accountId)).concat(" does´t exist"))).getBalance();
    }

    @Override
    @Transactional
    public void transfer(Long origin, Long destiny, BigDecimal balance, Long bankId) {
        Account originAccount = accountRepository.findById(origin)
                .orElseThrow(() -> new NoSuchElementException("the account with id: ".concat(String.valueOf(origin)).concat(" does´t exist")));
        Account destinyAccount = accountRepository.findById(destiny)
                .orElseThrow(() -> new NoSuchElementException("the account with id: ".concat(String.valueOf(destiny)).concat(" does´t exist")));

        originAccount.debit(balance);
        accountRepository.save(originAccount);

        destinyAccount.credit(balance);
        accountRepository.save(destinyAccount);

       Bank bank = bankRepository.findById(bankId)
               .orElseThrow(() -> new NoSuchElementException("the bank with id: ".concat(String.valueOf(bankId)).concat(" does´t exist")));
        int totalTransfer = bank.getTotalTransfer();
        bank.setTotalTransfer(++totalTransfer);
        bankRepository.save(bank);
    }
}
