package com.example.OperacaoBancaria.repository;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class ContaBancariaRepository implements Serializable {

    @Id
    private Long id;

    private int balance;

}
