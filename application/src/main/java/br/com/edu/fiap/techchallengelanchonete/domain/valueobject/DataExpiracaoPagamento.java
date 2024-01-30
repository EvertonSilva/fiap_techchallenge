package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataExpiracaoPagamento {
    private static final Integer TEMPO_RESTANTE_MINUTOS = 30;
    private Date data;

    public static DataExpiracaoPagamento DataExpiracaoPagamentoPadrao() {
        return new DataExpiracaoPagamento(Date.from(OffsetDateTime.now().plusMinutes(TEMPO_RESTANTE_MINUTOS).toInstant()));
    }
}
