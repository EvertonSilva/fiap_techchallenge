package br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "clientes")
@Entity
@EqualsAndHashCode(callSuper = true)
public class ClienteModel extends DomainObject {
    private String nome;
    private String email;
    private String cpf;

    public ClienteModel(){
        super();
    }

    public ClienteModel(long id, String nome, String email, String cpf){
        super(id);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public ClienteModel(String nome, String email, String cpf){
        super();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }
}
