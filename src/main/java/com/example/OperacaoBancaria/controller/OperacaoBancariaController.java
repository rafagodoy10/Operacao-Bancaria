package com.example.OperacaoBancaria.controller;

import com.example.OperacaoBancaria.domain.ContaBancariaRequest;
import com.example.OperacaoBancaria.domain.ContaBancariaResponse;
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

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/reset" ,method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void reset() {
        operacaoBancariaService.reset();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ContaBancariaResponse event(@RequestBody ContaBancariaRequest contaBancariaRequest) {
        return operacaoBancariaService.Operacao(contaBancariaRequest);
    }

    @RequestMapping(value = "/balance/{account_id}")
    public String getByAccountId(@PathVariable Long accountId) {
        return operacaoBancariaService.getByAccountId(accountId);
    }

}
