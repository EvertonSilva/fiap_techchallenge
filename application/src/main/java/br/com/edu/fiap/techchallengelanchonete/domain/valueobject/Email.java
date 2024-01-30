package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    public static final String EMAIL_PAGAMENTO = "pagamento@techchallengelanchonete.com";

    private String valor;
}
