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
public class ContaBancariaRequest implements Serializable {

    @NotNull
    private String type;

    @NotNull
    private Long id;

    @NotNull
    private int amount;

    private Long idDestino;

}
