package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.adapter.ClienteAdapter;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteUseCase {
    private ClienteRepository clienteRepository;
    private ClienteAdapter clienteAdapter;

    @Autowired
    public ClienteUseCase(ClienteRepository clienteRepository, ClienteAdapter clienteAdapter) {
        this.clienteRepository = clienteRepository;
        this.clienteAdapter = clienteAdapter;
    }

    public ClienteNulo semIdentificacaoCliente(Cliente cliente) {
        return new ClienteNulo(new Id(0), new Nome("ClienteNulo"), new Email("nulo@nulo"), new CPF("00000000000"));
    }

    public Cliente salvaCliente(Cliente cliente) {
        return this.clienteExiste(cliente);
    }

    public Cliente autenticaCliente(Cliente cliente) {
        return this.clienteExiste(cliente);
    }

    private Cliente clienteExiste(Cliente cliente){
        var model = this.clienteAdapter.toModel(cliente);

        var clienteExiste = this.clienteRepository.findBycpf(model.getCpf());
        if (clienteExiste.isPresent())
            return this.clienteAdapter.toDomain(clienteExiste.get());
        else{
            model = this.clienteRepository.save(model);
            return this.clienteAdapter.toDomain(model);
        }
    }

}
