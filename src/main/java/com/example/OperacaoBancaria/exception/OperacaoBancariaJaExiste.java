package com.example.OperacaoBancaria.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class OperacaoBancariaJaExiste extends RuntimeException{
    public OperacaoBancariaJaExiste(String message) {
        super(message);
    }
}
