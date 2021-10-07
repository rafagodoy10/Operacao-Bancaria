package com.example.OperacaoBancaria.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ContaNaoEncontrada extends RuntimeException{
    public ContaNaoEncontrada(String message) {
        super(message);
    }
}
