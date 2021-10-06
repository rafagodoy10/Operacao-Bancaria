package com.example.OperacaoBancaria.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException  extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
