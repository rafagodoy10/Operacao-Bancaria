package com.example.OperacaoBancaria.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContaBancariaRequest implements Serializable {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "origin")
    private Long idOrigin;

    @JsonProperty(value = "amount")
    private BigDecimal amount;

    @JsonProperty(value = "destination")
    private Long idDestino;

}
