package org.cyrestrepo.test.springboot.app;

import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.model.Bank;

import java.math.BigDecimal;
import java.util.Optional;

public class Data {

    private Data(){
    }

    public static final Optional<Account> ACCOUNT_001 = Optional.of(new Account(1L, "Cristian", new BigDecimal("1000")));
    public static final Optional<Account> ACCOUNT_002 = Optional.of(new Account(2L, "Veronica", new BigDecimal("2000")));
    public static final Optional<Bank> BANK = Optional.of(new Bank(1L, "Bancolombia", 0));

    public static Optional<Account> createAccountMock001(){
       return Optional.of(new Account(1L, "Cristian", new BigDecimal("1000")));
    }

    public static Optional<Account> createAccountMock002(){
        return Optional.of(new Account(2L, "Veronica", new BigDecimal("2000")));
    }

    public static Optional<Bank> createBankMock() {
        return Optional.of(new Bank(1L, "Bancolombia", 0));
    }
}
