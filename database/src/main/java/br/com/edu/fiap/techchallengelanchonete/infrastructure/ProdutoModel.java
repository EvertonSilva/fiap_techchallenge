package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToOne(targetEntity = CategoriaModel.class)
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;

}
