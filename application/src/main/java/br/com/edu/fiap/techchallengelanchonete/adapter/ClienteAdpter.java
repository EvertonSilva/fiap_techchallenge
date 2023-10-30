package br.com.edu.fiap.techchallengelanchonete.adapter;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Produto;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteAdpter implements IAdapter<Cliente, ClienteModel> {

    @Override
    public Cliente toDomain(ClienteModel clienteModel) {
        if (clienteModel == null)
            return null;

        var cliente = new Cliente();

        cliente.setId(new Id(clienteModel.getId()));
        // TODO: Implementar o restante quando o merge com a branch de produto acontecer.

        return cliente;
    }

    @Override
    public ClienteModel toModel(Cliente cliente) {
        if (cliente == null)
            return null;

        var clienteModel = new ClienteModel();

        clienteModel.setId(cliente.getId().getValor());
        // TODO: Implementar o restante quando o merge com a branch de produto acontecer.

        return clienteModel;
    }
}
