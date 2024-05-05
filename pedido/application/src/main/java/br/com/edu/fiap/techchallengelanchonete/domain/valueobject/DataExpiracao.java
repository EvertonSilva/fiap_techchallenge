package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataExpiracao {
    private static final Integer TEMPO_RESTANTE_MINUTOS = 30;
    private Date data;

    public static DataExpiracao ExpiracaoPadraoPagamento() {
        return new DataExpiracao(Date.from(Instant.now().plus(TEMPO_RESTANTE_MINUTOS, ChronoUnit.MINUTES)));
    }
}
