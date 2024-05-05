package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoCopiaCola extends PagamentoPIX {
    private String valor;
}
