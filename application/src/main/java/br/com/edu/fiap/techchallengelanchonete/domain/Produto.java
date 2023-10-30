package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {
    private Nome nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
//    private List<Imagem> imagens;
}
