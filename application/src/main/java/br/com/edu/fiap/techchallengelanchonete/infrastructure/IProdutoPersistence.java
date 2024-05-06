package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoPersistence {
    Produto cadastro(Produto produto);
    Produto edita(Long id, Produto produto);
    void exclui(Long id);
    List<Produto> listagem();
    Optional<Produto> buscaId(Long id);
    List<Produto> buscaCategoria(String descricao);
}
