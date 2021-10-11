package com.example.OperacaoBancaria.service;

import com.example.OperacaoBancaria.domain.ContaBancariaResponse;
import com.example.OperacaoBancaria.repository.ContaBancariaModel;
import com.example.OperacaoBancaria.domain.ContaBancariaRequest;
import com.example.OperacaoBancaria.exception.ContaNaoEncontrada;
import com.example.OperacaoBancaria.repository.OperacaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class OperacaoBancariaService {

    @Autowired
    OperacaoBancariaRepository operacaoBancariaRepository;

    @Autowired
    OperacoesServices operacoesServices;

    public void reset() {
        operacaoBancariaRepository.deleteAll();
    }

    public ContaBancariaResponse Operacao(ContaBancariaRequest contaBancariaRequest) {

        ContaBancariaModel retornoDeposit = null;
        ContaBancariaModel retornoSaque = null;

        switch (contaBancariaRequest.getType()) {
            case "deposit":
                retornoDeposit = operacoesServices.criaDeposita(contaBancariaRequest);
                break;
            case "withdraw":
                retornoSaque = operacoesServices.saque(contaBancariaRequest);
                break;
            case "transfer":
                ContaBancariaModel retornoConta = operacoesServices.buscaConta(contaBancariaRequest.getIdOrigin());
                if (Objects.nonNull(retornoConta)) {
                    retornoDeposit = operacoesServices.criaDeposita(contaBancariaRequest);
                    retornoSaque = operacoesServices.saque(contaBancariaRequest);
                    break;
                } else {
                    throw new ContaNaoEncontrada("Operação bancaria inválida");
                }
        }
        return ContaBancariaResponse.builder().contaBancariaOrigemModel(retornoSaque)
                .contaBancariaDestinoModel(retornoDeposit)
                .build();
    }

    public BigDecimal getByAccountId(Long accountId) {
        ContaBancariaModel retorno = operacaoBancariaRepository.findById(accountId).orElse(null);
        if (Objects.isNull(retorno)) {
            throw new ContaNaoEncontrada("Conta não encontrada");
        }
        return retorno.getBalance();
    }
}
