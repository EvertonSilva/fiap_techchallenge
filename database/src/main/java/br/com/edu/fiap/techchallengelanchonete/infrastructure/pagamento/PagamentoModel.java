package br.com.edu.fiap.techchallengelanchonete.infrastructure.pagamento;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "pagamentos")
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoModel extends DomainObject {
    @Column
    private String statusPagamento;
    @Column
    private String pixCopiaECola;
    @Column(length = 5120)
    private String pixQRCode64;
    @Column
    private Date dataExpiracaoPagamento;
}
