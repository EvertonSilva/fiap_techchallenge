package br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.produto.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
}
