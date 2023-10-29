package br.com.edu.fiap.techchallengelanchonete.domain;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import lombok.Data;

@Data
public abstract class DomainObject {
    private Id id;

    public DomainObject(){}

    public DomainObject(Id id){
        this.id = id;
    }
}
