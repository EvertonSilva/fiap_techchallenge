package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Descricao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Valor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper=true)
public class Produto extends DomainObject {
    private Nome nome;
    private Descricao descricao;
    private Valor preco;
    private Categoria categoria;
    private List<Imagem> imagens;

    public Produto() {
        this.nome = new Nome("");
        this.descricao = new Descricao("");
        this.preco = new Valor(new BigDecimal(0));
        this.categoria = new Categoria();
        this.imagens = new ArrayList<>();
    }
}
