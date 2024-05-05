package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

public enum StatusPedido {
    AGUARDANDO_PAGAMENTO,
    RECEBIDO,
    EM_PREPARACAO,
    PRONTO,
    FINALIZADO,
    CANCELADO;

    public static StatusPedido de(String status) {
        try {
            if (status == null)
                throw new IllegalArgumentException();

            return Enum.valueOf(StatusPedido.class, status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw  new ApplicationException("Status não existe");
        }
    }
}
