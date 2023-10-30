package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Produto {
    // FIXME: Trocar id por Value Object.
    private Long id;
    private Nome nome;
    private String descricao;
    private BigDecimal preco;
    private Categoria categoria;
    private List<Imagem> imagens;

    public Produto() {
        // TODO: Implementar o restante quando o merge com a branch de produto acontecer.
        this.nome = new Nome("");
        this.preco = new BigDecimal(0);
    }
}
