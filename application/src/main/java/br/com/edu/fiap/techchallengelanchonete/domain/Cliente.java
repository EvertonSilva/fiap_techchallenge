package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;

@Data
public class Cliente {
    private Nome nome;
    private String email;
    private String cpf;
}
