package com.example.OperacaoBancaria.service;

import com.example.OperacaoBancaria.domain.OperacaoBancaria;
import com.example.OperacaoBancaria.exception.OperacaoBancariaJaExiste;
import com.example.OperacaoBancaria.exception.ValidationException;
import com.example.OperacaoBancaria.repository.OperacaoBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.util.Objects.isNull;

@Service
public class OperacaoBancariaService {

    @Autowired
    OperacaoBancariaRepository operacaoBancariaRepository;

    public void create(OperacaoBancaria operacaoBancaria) {

        if (isNull(operacaoBancaria.getAmount()) ||
                isNull(operacaoBancaria.getDestination()) ||
                isNull(operacaoBancaria.getType())) {
            throw new ValidationException();
        }

        verificaExisteTabela(operacaoBancaria);
        save(operacaoBancaria);
    }

    private void verificaExisteTabela(OperacaoBancaria operacaoBancaria) {
        OperacaoBancaria productFound = operacaoBancariaRepository.findOne(operacaoBancaria.getId());
        if (Objects.nonNull(productFound)) {
            throw new OperacaoBancariaJaExiste("Product Already Exist");
        }
    }

    private void save(OperacaoBancaria productFound) {
        operacaoBancariaRepository.save(productFound);
    }
}
