package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "categorias")
@EqualsAndHashCode(callSuper = true)
public class CategoriaModel extends DomainObject {
    private String nome;
}
