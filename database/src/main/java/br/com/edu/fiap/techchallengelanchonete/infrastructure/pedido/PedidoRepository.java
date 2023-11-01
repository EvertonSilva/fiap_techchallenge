package br.com.edu.fiap.techchallengelanchonete.infrastructure.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
    /*
    @@Query("SELECT p.status AS status, p.itens AS itens FROM Pedido p")
    List<Map<StatusPedido, List<ItemPedido>>> groupPedidosByStatus();
=====================================
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT sp, p FROM StatusPedido sp LEFT JOIN sp.pedidos p")
    List<Object[]> findPedidosGroupedByStatus();
}
====================================
Uso
====================================
@Autowired
    private PedidoRepository pedidoRepository;

    public List<StatusPedido> getStatusPedidosWithPedidos() {
        List<Object[]> results = pedidoRepository.findPedidosGroupedByStatus();
        Map<StatusPedido, List<Pedido>> statusPedidosMap = new HashMap<>();

        for (Object[] result : results) {
            StatusPedido statusPedido = (StatusPedido) result[0];
            Pedido pedido = (Pedido) result[1];

            statusPedidosMap
                .computeIfAbsent(statusPedido, k -> new ArrayList<>())
                .add(pedido);
        }

        List<StatusPedido> statusPedidos = new ArrayList<>(statusPedidosMap.keySet());
        statusPedidos.forEach(sp -> sp.setPedidos(statusPedidosMap.get(sp)));

        return statusPedidos;
    }
    * */
}
