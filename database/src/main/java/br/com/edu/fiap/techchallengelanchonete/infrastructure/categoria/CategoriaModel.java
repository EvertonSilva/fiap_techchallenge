package br.com.edu.fiap.techchallengelanchonete.infrastructure.categoria;

import br.com.edu.fiap.techchallengelanchonete.infrastructure.DomainObject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "categorias")
@EqualsAndHashCode(callSuper = true)
public class CategoriaModel extends DomainObject {
    private String nome;
}
