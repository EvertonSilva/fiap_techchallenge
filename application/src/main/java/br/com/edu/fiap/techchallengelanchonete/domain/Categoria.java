package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
/**
 * Categorias fixas:
    - Lanche;
    - Acompanhamento;
    - Bebida;
    - Sobremesa
 */
public class Categoria extends DomainObject {
    private Nome nome;

    public Categoria() {
        this.nome = new Nome("");
    }
}
