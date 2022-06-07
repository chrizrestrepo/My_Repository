package com.prueba.tecnica.titamedia.model.service;

import com.prueba.tecnica.titamedia.model.entity.Debt;
import com.prueba.tecnica.titamedia.model.repository.DebtRepository;
import com.prueba.tecnica.titamedia.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Primary
public class PayService implements IPayService{

    private final UserRepository userRepository;
    private final DebtRepository debtRepository;

    @Autowired
    public PayService(UserRepository userRepository, DebtRepository debtRepository) {
        this.userRepository = userRepository;
        this.debtRepository = debtRepository;
    }

    @Override
    public List<String> getBanksByUserId(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("the user with id: "
                        .concat(userId.toString())
                        .concat(" Not found")))
                .getDebts().stream()
                .map(debt -> debt.getBank().getBankName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Debt> getDebtsByUserIdAndBank(Long userId, String bankName){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("the user with id: "
                        .concat(userId.toString())
                        .concat(" Not found")))
                .getDebts().stream()
                .filter(debt -> debt.getBank().getBankName().equals(bankName))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void payDebt(Long userId, String bankName, String payReference, Long value){
        userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("the user with id: "
                        .concat(userId.toString())
                        .concat(" Not found")))
                .getDebts().stream()
                .filter(debt -> debt.getBank().getBankName().equals(bankName))
                .filter(debt -> debt.getPayReference().equalsIgnoreCase(payReference))
                .forEach(debt -> {
                    Long totalBalance = debt.getOutstandingBalance() - value;
                    if(totalBalance < 0){
                        throw new IllegalArgumentException("the total balance can't be less than zero");
                    }
                    debt.setOutstandingBalance(totalBalance);
                    debtRepository.save(debt);
                });
    }
}
