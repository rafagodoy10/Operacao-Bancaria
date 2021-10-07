package com.example.OperacaoBancaria.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaBancariaResponse implements Serializable {

    private String type;

    @NotNull
    private ContaOrigem contaOrigem;

    @NotNull
    private ContaDestino contaDestino;

}
