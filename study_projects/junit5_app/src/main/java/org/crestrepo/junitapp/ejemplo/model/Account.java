package org.crestrepo.junitapp.ejemplo.model;

import lombok.Getter;
import lombok.Setter;
import org.crestrepo.junitapp.ejemplo.exception.InsufficientAmmount;

import java.math.BigDecimal;

@Getter
@Setter
public class Account {
    private String personName;
    private BigDecimal balance;
    private Bank bank;

    public Account(String personName, BigDecimal balance){
        this.personName = personName;
        this.balance = balance;
    }

    public void debit(BigDecimal mount){
        if(this.getBalance().subtract(mount).intValue() < 0)
            throw new InsufficientAmmount("insufficient amount");

        this.setBalance(this.getBalance().subtract(mount));
    }

    public void credit(BigDecimal mount){
        this.setBalance(this.getBalance().add(mount));
    }
}
