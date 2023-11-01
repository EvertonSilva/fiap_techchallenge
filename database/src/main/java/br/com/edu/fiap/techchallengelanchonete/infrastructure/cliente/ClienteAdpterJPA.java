package br.com.edu.fiap.techchallengelanchonete.infrastructure.cliente;

import br.com.edu.fiap.techchallengelanchonete.adapter.ClienteAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClienteAdpterJPA implements IClientePersistence {

    private ClienteRepository clienteRepository;
    private ClienteAdapter clienteAdapter;

    @Autowired
    public ClienteAdpterJPA(ClienteRepository clienteRepository, ClienteAdapter clienteAdapter) {
        this.clienteRepository = clienteRepository;
        this.clienteAdapter = clienteAdapter;
    }

    @Override
    public Cliente cadastro(Cliente cliente) {
        return clienteAdapter.toDomain(this.clienteRepository.save(clienteAdapter.toModel(cliente)));
    }

    @Override
    public Cliente buscaCPF(CPF cpf) {
        var optionalClienteModel = clienteRepository.findBycpf(cpf.getValor());

        return  optionalClienteModel.isPresent() ?
                clienteAdapter.toDomain(optionalClienteModel.get()) : new ClienteNulo();
    }
}
