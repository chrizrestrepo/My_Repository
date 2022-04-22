package org.cyrestrepo.test.springboot.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cyrestrepo.test.springboot.app.exception.InsufficientBalanceException;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name")
    private String personName;
    private BigDecimal balance;

    public void debit(BigDecimal amount){
        BigDecimal newBalance = this.balance.subtract(amount);
        if(newBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new InsufficientBalanceException("the amount beats the allowed value: ".concat(String.valueOf(newBalance.longValue())));
        }
        this.balance = newBalance;
    }

    public void credit(BigDecimal mount){
        this.balance = this.balance.add(mount);
    }
}
