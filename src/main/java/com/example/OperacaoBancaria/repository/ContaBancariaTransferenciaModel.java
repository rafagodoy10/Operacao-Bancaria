package com.example.OperacaoBancaria.repository;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ContaBancariaTransferenciaModel implements Serializable {

    @Id
    private Long idOrigem;

    private BigDecimal balanceOrigem;

    private Long idDestino;

    private BigDecimal balanceDestino;
}
