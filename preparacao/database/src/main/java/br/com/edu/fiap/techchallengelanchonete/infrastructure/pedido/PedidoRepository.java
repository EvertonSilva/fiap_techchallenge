package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    List<PedidoModel> findByStatusPedido(String statusPedido);
    Optional<PedidoModel> findByCodigo(String codigo);
}
