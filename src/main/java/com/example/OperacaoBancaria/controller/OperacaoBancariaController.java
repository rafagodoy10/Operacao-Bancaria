package com.example.OperacaoBancaria.controller;

import com.example.OperacaoBancaria.domain.OperacaoBancaria;
import com.example.OperacaoBancaria.service.OperacaoBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operacaoBancaria")
public class OperacaoBancariaController {

    @Autowired
    OperacaoBancariaService operacaoBancariaService;

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void post(@RequestBody OperacaoBancaria operacaoBancaria) {
        operacaoBancariaService.create(operacaoBancaria);
    }


}
