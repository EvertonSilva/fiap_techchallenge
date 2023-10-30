package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;

@Data
public class Cliente {
    // FIXME: Trocar id por Value Object.
    private Long id;
    private Nome nome;
    private String email;
    private String cpf;

    public Cliente() {
        // TODO: Implementar o restante quando o merge com a branch de cliente acontecer.
        this.nome = new Nome("");
    }
}
