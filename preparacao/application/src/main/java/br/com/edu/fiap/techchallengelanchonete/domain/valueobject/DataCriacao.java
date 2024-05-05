package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
public class DataCriacao {
    private Date valor;

    public DataCriacao() {
        this.valor = Date.from(Instant.now());
    }
}
