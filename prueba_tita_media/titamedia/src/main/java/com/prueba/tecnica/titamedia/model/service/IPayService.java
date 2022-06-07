package com.prueba.tecnica.titamedia.model.service;

import com.prueba.tecnica.titamedia.model.entity.Debt;

import java.util.List;

public interface IPayService {

    List<String> getBanksByUserId(Long userId);
    List<Debt> getDebtsByUserIdAndBank(Long userId, String bankName);
    void payDebt(Long userId, String bankName, String payReference, Long value);

}
