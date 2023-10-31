package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteAdapter implements IAdapter<Cliente, ClienteModel> {

    @Override
    public Cliente toDomain(ClienteModel clienteModel) {
        if (clienteModel == null)
            return null;

        return new Cliente(new Id(clienteModel.getId()),
                new Nome(clienteModel.getNome()),
                new Email(clienteModel.getEmail()),
                new CPF(clienteModel.getCpf()));
    }

    @Override
    public ClienteModel toModel(Cliente cliente) {
        if (cliente == null)
            return null;

        ClienteModel clienteModel = new ClienteModel();

        if(cliente.getId() != null)
            clienteModel.setId(cliente.getId().getValor());

        if(cliente.getNome() != null)
            clienteModel.setNome(cliente.getNome().getValor());

        clienteModel.setEmail(cliente.getEmail().getValor());
        clienteModel.setCpf(cliente.getCpf().getValor());

        return clienteModel;
    }
}
