package br.com.edu.fiap.techchallengelanchonete.infrastructure.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    Optional<ProdutoModel> findByNome(String nome);
    List<ProdutoModel> findByCategoria_Nome(String nome);
}
