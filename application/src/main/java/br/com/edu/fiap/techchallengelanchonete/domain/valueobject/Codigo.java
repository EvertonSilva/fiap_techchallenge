package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Codigo {
    private String valor;

    public void gerarCodigo() {
        this.valor = UUID.randomUUID().toString();
    }
}
