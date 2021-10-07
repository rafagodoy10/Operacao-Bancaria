package com.example.OperacaoBancaria.service;

import com.example.OperacaoBancaria.domain.ContaBancariaResponse;
import com.example.OperacaoBancaria.domain.ContaDestino;
import com.example.OperacaoBancaria.domain.ContaOrigem;
import com.example.OperacaoBancaria.repository.ContaBancariaRepository;
import com.example.OperacaoBancaria.domain.ContaBancariaRequest;
import com.example.OperacaoBancaria.exception.ContaNaoEncontrada;
import com.example.OperacaoBancaria.repository.OperacaoBancariaRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import java.util.Objects;

@Service
public class OperacaoBancariaService {

    @Autowired
    OperacaoBancariaRepository operacaoBancariaRepository;

    @Autowired
    ContaBancariaRepository contaBancariaRepository;

    @Autowired
    ValidaParametros validaParametros;

    @Autowired
    ContaBancariaResponse contaBancariaResponse;

    @Autowired
    ContaOrigem contaOrigem;

    @Autowired
    ContaDestino contaDestino;

    public void reset() {
        operacaoBancariaRepository.deleteAll();
    }

    public ContaBancariaResponse Operacao(ContaBancariaRequest contaBancariaRequest) {

        switch (contaBancariaRequest.getType()){
            case  "deposit":
                var retornoDeposit = criaDeposita(contaBancariaRequest);

                contaDestino.setId(retornoDeposit.getId());
                contaDestino.setBalance(retornoDeposit.getBalance());
                contaBancariaResponse.setContaDestino(contaDestino);
                contaBancariaResponse.setType("deposit");
                return contaBancariaResponse;
            case "withdraw":
                var retornoSaque = saque(contaBancariaRequest);
                contaOrigem.setId(retornoSaque.getId());
                contaOrigem.setBalance(retornoSaque.getBalance());
                contaBancariaResponse.setContaOrigem(contaOrigem);
                contaBancariaResponse.setType("origin");
                return contaBancariaResponse;
            case "transfer":
                var retornoTransferencia = Transferencia(contaBancariaRequest);

                return contaBancariaResponse;
        }
        return null;
    }

    public String getByAccountId(Long accountId) {
        return buscarConta(accountId);
    }

    private String buscarConta(Long accountId) {
        var retorno = operacaoBancariaRepository.findOne(accountId);
        if (Objects.isNull(retorno)){
            throw new ContaNaoEncontrada("Conta não encontrada");
        }
        return null;
    }

    private ContaBancariaRepository criaDeposita(ContaBancariaRequest contaBancariaRequest) {
        validaParametros.insertAndWithdraw(contaBancariaRequest);
        ContaBancariaRepository retorno = operacaoBancariaRepository.findOne(contaBancariaRequest.getId());

        if (Objects.isNull(retorno)) {
            contaBancariaRepository.setId(contaBancariaRequest.getId());
            contaBancariaRepository.setBalance(contaBancariaRequest.getAmount());
            operacaoBancariaRepository.save(contaBancariaRepository);
        }else{
            retorno.setBalance(retorno.getBalance() + contaBancariaRequest.getAmount());
            operacaoBancariaRepository.save(retorno);
        }
        return retorno;
    }

    private ContaBancariaRepository saque(ContaBancariaRequest contaBancariaRequest){
        validaParametros.insertAndWithdraw(contaBancariaRequest);
        ContaBancariaRepository retorno = operacaoBancariaRepository.findOne(contaBancariaRequest.getId());

        if (Objects.nonNull(retorno)) {
            retorno.setBalance(retorno.getBalance() - contaBancariaRequest.getAmount());
            operacaoBancariaRepository.save(retorno);
        }else{
            throw new ContaNaoEncontrada("Conta não encontrada");
        }
        return retorno;
    }

    private ContaBancariaResponse Transferencia(ContaBancariaRequest contaBancariaRequest) {
        validaParametros.insertAndWithdraw(contaBancariaRequest);
        ContaBancariaRepository retornoOrigem = operacaoBancariaRepository.findOne(contaBancariaRequest.getId());
        ContaBancariaRepository retornoDestino = operacaoBancariaRepository.findOne(contaBancariaRequest.getIdDestino());

        if (Objects.nonNull(retornoOrigem) && Objects.nonNull(retornoDestino))  {
            retornoOrigem.setBalance(retornoOrigem.getBalance() - contaBancariaRequest.getAmount());
            retornoDestino.setBalance(retornoDestino.getBalance() + contaBancariaRequest.getAmount());
            operacaoBancariaRepository.save(retornoOrigem);
            operacaoBancariaRepository.save(retornoDestino);
        }else{
            throw new ContaNaoEncontrada("Conta não encontrada");
        }
        contaOrigem.setId(retornoOrigem.getId());
        contaOrigem.setBalance(retornoOrigem.getBalance());
        contaDestino.setId(retornoOrigem.getId());
        contaDestino.setBalance(retornoOrigem.getBalance());

        return new ContaBancariaResponse("transfer", contaOrigem, contaDestino);
    }
}
