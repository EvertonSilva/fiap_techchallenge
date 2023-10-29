package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;

/*
    Categorias fixas:
    - Lanche;
    - Acompanhamento;
    - Bebida;
    - Sobremesa
*/
@Data
public class Categoria {
    private Nome nome;
}
