package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteAdapter implements IAdapter<Cliente, ClienteModel> {

    @Override
    public Cliente toDomain(ClienteModel clienteModel) {
        if (clienteModel == null)
            return new ClienteNulo();

        return Cliente.builder()
            .id(new Id(clienteModel.getId()))
            .nome(new Nome(clienteModel.getNome()))
            .email(new Email(clienteModel.getEmail()))
            .cpf(new CPF(clienteModel.getCpf()))
            .build();
    }

    @Override
    public ClienteModel toModel(Cliente cliente) {
        if (cliente == null)
            return null;

        ClienteModel clienteModel = new ClienteModel();

        if(cliente.getId() != null && cliente.getId().getValor() > 0)
            clienteModel.setId(cliente.getId().getValor());

        if(cliente.getNome() != null)
            clienteModel.setNome(cliente.getNome().getValor());

        clienteModel.setEmail(cliente.getEmail().getValor());
        clienteModel.setCpf(cliente.getCpf().getValor());

        return clienteModel;
    }
}
