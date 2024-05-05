package br.com.edu.fiap.techchallengelanchonete.infrastructure;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;

public interface IClientePersistence {
    Cliente cadastro(Cliente cliente);
    Cliente buscaCPF(CPF cpf);
    Cliente buscaId(Long id);
}
