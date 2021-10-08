package com.example.OperacaoBancaria.domain;


import com.example.OperacaoBancaria.repository.ContaBancariaModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(value = "origin")
    private ContaBancariaModel contaBancariaOrigemModel;

    @JsonProperty(value = "destination")
    private ContaBancariaModel contaBancariaDestinoModel;

}
