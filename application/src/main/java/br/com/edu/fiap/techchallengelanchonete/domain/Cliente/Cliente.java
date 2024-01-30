package br.com.edu.fiap.techchallengelanchonete.domain.Cliente;

import br.com.edu.fiap.techchallengelanchonete.domain.DomainObject;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class Cliente extends DomainObject implements ICliente {
    private Nome nome;
    private Email email;

    @NotNull
    private CPF cpf;

    public Cliente(){
        this.nome = new Nome("");
        this.cpf = new CPF("");
        this.email = new Email("");
    }

    public Cliente(Id id, Nome nome, Email email, CPF cpf){
        super(id);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(Email email, CPF cpf){
        super(new Id(Long.valueOf(0)));
        this.nome = new Nome("");
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(Nome nome, Email email, CPF cpf){
        super(new Id(Long.valueOf(0)));
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public String getPrimeiroNome() {
        return this.getNome().getPrimeiro();
    }

    public String getSobrenome() {
        return this.getNome().getSobrenome();
    }
}
