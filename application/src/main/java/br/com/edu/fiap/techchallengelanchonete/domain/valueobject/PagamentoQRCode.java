package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoQRCode extends PagamentoPIX {
    private String valor;
}
