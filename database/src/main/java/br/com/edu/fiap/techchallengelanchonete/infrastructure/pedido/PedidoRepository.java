package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    List<PedidoModel> findByStatusPedido(String statusPedido);
}
