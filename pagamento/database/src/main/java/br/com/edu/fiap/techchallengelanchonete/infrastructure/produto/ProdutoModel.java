package br.com.edu.fiap.techchallengelanchonete.infrastructure.produto;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria.CategoriaModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "produtos")
@EqualsAndHashCode(callSuper = true)
public class ProdutoModel extends DomainObject {
    @Column
    private String nome;

    private String descricao;

    private BigDecimal preco;

    @ManyToOne(targetEntity = CategoriaModel.class)
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;

}
