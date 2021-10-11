package com.example.OperacaoBancaria.service;

import com.example.OperacaoBancaria.domain.ContaBancariaRequest;
import com.example.OperacaoBancaria.exception.ContaNaoEncontrada;
import com.example.OperacaoBancaria.exception.ValidationException;
import com.example.OperacaoBancaria.repository.ContaBancariaModel;
import com.example.OperacaoBancaria.repository.OperacaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class OperacoesServices {

    @Autowired
    OperacaoBancariaRepository operacaoBancariaRepository;

    public ContaBancariaModel criaDeposita(ContaBancariaRequest contaBancariaRequest) {
        if (isNull(contaBancariaRequest.getAmount())) {
            throw new ValidationException();
        }

        ContaBancariaModel retorno = buscaConta(contaBancariaRequest.getIdDestino());

        if (Objects.isNull(retorno)) {
            ContaBancariaModel contaBancariaModel = new ContaBancariaModel(contaBancariaRequest.getIdDestino(), contaBancariaRequest.getAmount());
            operacaoBancariaRepository.save(contaBancariaModel);
            return contaBancariaModel;
        } else {
            retorno.setBalance(retorno.getBalance().add(contaBancariaRequest.getAmount()));
            operacaoBancariaRepository.save(retorno);
        }
        return retorno;
    }

    public ContaBancariaModel buscaConta(Long id) {
        if(Objects.nonNull(id)) {
            return operacaoBancariaRepository.findById(id).orElse(null);
        }
        return null;
    }

    public ContaBancariaModel saque(ContaBancariaRequest contaBancariaRequest) {
        if (isNull(contaBancariaRequest.getIdOrigin()) ||
                isNull(contaBancariaRequest.getAmount())) {
            throw new ValidationException();
        }

        ContaBancariaModel retorno = buscaConta(contaBancariaRequest.getIdOrigin());

        if (Objects.nonNull(retorno)) {
            retorno.setBalance(retorno.getBalance().subtract(contaBancariaRequest.getAmount()));
            operacaoBancariaRepository.save(retorno);
        } else {
            throw new ContaNaoEncontrada("Conta não encontrada");
        }
        return retorno;
    }
}
