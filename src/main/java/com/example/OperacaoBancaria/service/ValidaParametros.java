package com.example.OperacaoBancaria.service;

import com.example.OperacaoBancaria.domain.ContaBancariaRequest;
import com.example.OperacaoBancaria.exception.ValidationException;

import static java.util.Objects.isNull;

public class ValidaParametros {

    public void insertAndWithdraw(ContaBancariaRequest contaBancariaRequest) {

        if (isNull(contaBancariaRequest.getId()) ||
                isNull(contaBancariaRequest.getAmount())) {
            throw new ValidationException();
        }
    }

    public void transferencia(ContaBancariaRequest contaBancariaRequest) {

        if (isNull(contaBancariaRequest.getId()) ||
                isNull(contaBancariaRequest.getIdDestino()) ||
                isNull(contaBancariaRequest.getAmount())) {
            throw new ValidationException();
        }
    }
}
