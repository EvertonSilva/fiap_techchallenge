package br.com.edu.fiap.techchallengelanchonete.domain.Cliente;

import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteModel;


public class ClienteAdpter {
    //@Override
    public Cliente toDomain(ClienteModel clienteModel) {
        return new Cliente(new Id(clienteModel.getId()),
                new Nome(clienteModel.getNome()),
                new Email(clienteModel.getEmail()),
                new CPF(clienteModel.getCpf()));
    }

    //@Override
    public ClienteModel toModel(Cliente cliente) {
        return new ClienteModel(cliente.getId().getValor(),
                cliente.getNome().getValor(),
                cliente.getEmail().getValor(),
                cliente.getCpf().getValor());
    }
}
