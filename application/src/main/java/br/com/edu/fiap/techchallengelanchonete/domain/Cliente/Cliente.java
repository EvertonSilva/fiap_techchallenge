package br.com.edu.fiap.techchallengelanchonete.domain.Cliente;

import br.com.edu.fiap.techchallengelanchonete.domain.DomainObject;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@AllArgsConstructor
@SuperBuilder
public class Cliente extends DomainObject implements ICliente {
    private Nome nome;
    private Email email;

    @NotNull
    private CPF cpf;

    public Cliente() {
        this.nome = new Nome("");
        this.cpf = new CPF("");
        this.email = new Email("");
    }

    public String getPrimeiroNome() {
        if (nome == null)
            return "";

        return this.getNome().getPrimeiro();
    }

    public String getSobrenomes() {
        if (nome == null)
            return "";

        return this.getNome().getSobrenomes();
    }
}
