package br.com.fiap.techchallengelanchonete.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

import br.com.edu.fiap.techchallengelanchonete.domain.DomainObject;

class DomainObjectTest {
    
    @Test
    void deveCriarDomainObjectVazio() {
        var domainObject = new DomainObject() {};

        assertAll("Atributos vazios", 
            () -> assertThat(domainObject).isNotNull(),
            () -> assertThat(domainObject.getId()).isNotNull()
        );
    }

}
