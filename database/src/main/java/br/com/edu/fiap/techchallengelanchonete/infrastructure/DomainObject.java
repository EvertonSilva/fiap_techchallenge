package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class DomainObject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public DomainObject() {
    }

    public DomainObject(long id) {
    }

    // Getters and setters, if needed

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
