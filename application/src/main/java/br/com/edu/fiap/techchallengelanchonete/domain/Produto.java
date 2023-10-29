package br.com.edu.fiap.techchallengelanchonete.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Produto {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private List<Imagem> imagens;
}
