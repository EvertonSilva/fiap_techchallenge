package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.QRCodePagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Pagamento {
    private StatusPagamento status;

    public Pagamento() {
        this.status = StatusPagamento.AGUARDANDO;
    }

    // Cria lógica fake para geração de QRCode de pagamento que será gerado pelo Mercado Pago.
    public QRCodePagamento geraQRCodeMercadoPago(Pedido pedido) {
        return new QRCodePagamento(UUID.fromString(pedido.toString()).toString());
    }
}