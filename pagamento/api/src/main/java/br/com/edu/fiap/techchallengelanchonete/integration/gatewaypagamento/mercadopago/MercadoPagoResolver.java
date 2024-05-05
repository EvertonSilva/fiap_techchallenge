package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.mercadopago;

import br.com.edu.fiap.techchallengelanchonete.domain.StatusPagamento;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoResolver;
import com.mercadopago.client.payment.PaymentClient;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

// TODO: Descomentar após definições claras dos profiles x contextos de execução da app.
//@Profile("prd")
//@Component
public class MercadoPagoResolver implements IGatewayPagamentoResolver {
    private final String HOST_MERCADO_PAGO = "mercadopago";

    @Override
    public boolean validaGatewayPagamentoCorrente(Map<String, String> headers, Map<String, String> params, Map<String, Object> body) {
        // TODO: Implementar lógica completa de comparação com template.
        // @ref: https://www.mercadopago.com.br/developers/pt/docs/your-integrations/notifications/webhooks
        var rerefer = Optional.ofNullable(headers.get("referer"));
        var temRerefer = rerefer.isPresent() && rerefer.get().toLowerCase().contains(HOST_MERCADO_PAGO);

        var userAgent = Optional.ofNullable(headers.get("user-agent"));
        var temUserAgent = userAgent.isPresent() && userAgent.get().toLowerCase().contains(HOST_MERCADO_PAGO);

        var action = body.get("action");
        var actionPagamento = action.equals("payment.updated");

        return (temRerefer || temUserAgent) && actionPagamento;
    }

    public StatusPagamento interpretaStatusPagamento(Map<String, String> headers, Map<String, String> params, Map<String, Object> body) {
        try {
            var data = (Map<String, Object>)body.get("data");
            var id = data.get("id");

            var idPayment = Long.valueOf(String.valueOf(id));

            PaymentClient client = new PaymentClient();
            var statusPayment = client.get(idPayment).getStatus();

            return statusPayment.equals("approved") ?
                    StatusPagamento.APROVADO : StatusPagamento.REPROVADO;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao consultar status do pagamento no Mercado Pago", ex);
        }
    }
}
