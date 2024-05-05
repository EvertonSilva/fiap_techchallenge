package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Valor {
    private BigDecimal valor;
}
