package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;

public enum StatusPagamento {
    AGUARDANDO,
    APROVADO,
    REPROVADO;

    public static StatusPagamento de(String status) {
        try {
            if (status == null)
                throw new IllegalArgumentException();

            return Enum.valueOf(StatusPagamento.class, status.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw  new ApplicationException("Status não existe");
        }
    }
}
