package br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.mercadopago;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.Pedido;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.DataExpiracao;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoCopiaCola;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.PagamentoQRCode;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.integration.gatewaypagamento.IGatewayPagamentoRegistrador;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.resources.payment.Payment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO: Descomentar após definições claras dos profiles x contextos de execução da app.
//@Profile("prd")
@Component
public class GatewayMercadoPagoRegistrador implements IGatewayPagamentoRegistrador {
    @Override
    public void registroPagamento(Pedido pedido) {
        try {
            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            var urlCallback = new StringBuilder()
                    .append(ServletUriComponentsBuilder.fromCurrentRequestUri())
                    .append("/gatewaypagamento/pedido/")
                    .append(pedido.getCodigo().getValor())
                    .toString();

            var emailGeracaoPix = !(pedido.getCliente() instanceof ClienteNulo) ? pedido.getCliente().getEmail().getValor() : Email.EMAIL_PAGAMENTO;
            var dataExpiracao = DataExpiracao.ExpiracaoPadraoPagamento().getData();
            var paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(pedido.getValorTotal().getValor())
                            .description(pedido.toString())
                            .paymentMethodId("pix")
                            .notificationUrl(urlCallback)
                            .externalReference(pedido.getCodigo().getValor())
                            .dateOfExpiration(dataExpiracao.toInstant().atOffset(OffsetDateTime.now().getOffset()))
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(emailGeracaoPix)
                                            .firstName(pedido.getCliente().getPrimeiroNome())
                                            .lastName(pedido.getCliente().getSobrenomes())
                                            .identification(
                                                    IdentificationRequest.builder().type("CPF").number(pedido.getCliente().getCpf().getValor()).build())
                                            .build())
                            .build();

            var client = new PaymentClient();
            Payment payment = client.create(paymentCreateRequest, requestOptions);

            pedido.getPagamento().setDataExpiracaoPagamento(new DataExpiracao(dataExpiracao));
            pedido.getPagamento().setPixCopiaECola(new PagamentoCopiaCola(payment.getPointOfInteraction().getTransactionData().getQrCode()));
            pedido.getPagamento().setPixQRCode64(new PagamentoQRCode(payment.getPointOfInteraction().getTransactionData().getQrCodeBase64()));
        } catch (Exception ex) {
            throw new ApplicationException("Erro ao tentar criar pagamento no Mercado Pago");
        }
    }
}
