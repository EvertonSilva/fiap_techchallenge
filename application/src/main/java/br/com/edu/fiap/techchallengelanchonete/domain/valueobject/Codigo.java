package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Codigo {
    private String valor;

    public Codigo() {
        this.valor = UUID.randomUUID().toString();
    }
}
