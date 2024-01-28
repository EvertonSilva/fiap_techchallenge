package br.com.edu.fiap.techchallengelanchonete.domain.valueobject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nome {
    private String valor;

    public String getPrimeiro() {
        return valor.contains(" ") ? valor.split(" ")[0] : valor;
    }

    public String getSobrenome() {
        var ultimoNome = "";
        if (valor.contains(" ")) {
            var palavras = valor.split(" ");
            ultimoNome = palavras[palavras.length - 1];
        }
        return ultimoNome;
    }
}
