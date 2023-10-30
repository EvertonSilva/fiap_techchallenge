package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteModel;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteUseCase {
    @Autowired
    private ClienteRepository repo;

    public ClienteNulo semIdentificacaoCliente(Cliente cliente) {
        return new ClienteNulo(new Id(0), new Nome("ClienteNulo"), new Email("nulo@nulo"), new CPF("00000000000"));
    }

    public Cliente salvaCliente(Cliente cliente) {
        var model = this.adapterToClienteModel(cliente);
        model = repo.save(model);

        return this.adapterToCliente(model);
    }

    public Cliente autenticaCliente(Cliente cliente) {
        var model = adapterToClienteModel(cliente);
        var clienteExiste = repo.findByCPF(model.getCpf());

        return clienteExiste.map(this::adapterToCliente).orElseGet(() -> salvaCliente(cliente));
    }

    private ClienteModel adapterToClienteModel(Cliente model){
        return new ClienteModel(model.getId().getValor(),
                                model.getNome().getValor(),
                                model.getEmail().getValor(),
                                model.getCpf().getValor());
    }

    private Cliente adapterToCliente(ClienteModel model){
        return new Cliente(new Id(model.getId()),
                            new Nome(model.getNome()),
                            new Email(model.getEmail()),
                            new CPF(model.getCpf()));
    }

}
