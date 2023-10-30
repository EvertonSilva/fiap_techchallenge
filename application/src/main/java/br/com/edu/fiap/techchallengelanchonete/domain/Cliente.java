package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends DomainObject implements ICliente{
    private Nome nome;
    private Email email;
    private CPF cpf;

    public Cliente(){
        super();
    }

    public Cliente(Id id, Nome nome, Email email, CPF cpf){
        super(id);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
}
