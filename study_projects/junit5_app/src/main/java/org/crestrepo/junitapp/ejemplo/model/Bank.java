package org.crestrepo.junitapp.ejemplo.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Bank {
    private String name;
    private List<Account> accountList;

    public Bank(){
        this.accountList = new ArrayList<>();
    }

    public void addAccount(Account account){
        this.accountList.add(account);
        account.setBank(this);
    }

    public void transferMoney(Account origin, Account destiny, BigDecimal amount){
        origin.debit(amount);
        destiny.credit(amount);
    }
}
