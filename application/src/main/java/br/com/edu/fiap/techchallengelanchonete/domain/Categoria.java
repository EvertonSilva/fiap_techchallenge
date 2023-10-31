package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.adapter.CategoriaAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.*;

/*
    Categorias fixas:
    - Lanche;
    - Acompanhamento;
    - Bebida;
    - Sobremesa
*/
@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper=true)
public class Categoria extends DomainObject {
    private Nome nome;

    public Categoria() {
        this.nome = new Nome("");
    }
}
