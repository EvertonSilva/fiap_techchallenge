package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;

public class ClienteUseCase {
    private IClientePersistence clienteRepository;

    public ClienteUseCase(IClientePersistence clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvaCliente(Cliente cliente) {
        Cliente clienteExiste = this.clienteRepository.buscaCPF(cliente.getCpf());
        if (clienteExiste.getId() != null && clienteExiste.getId().getValor() > 0)
            throw new ApplicationException("Cliente já existe!");

        return this.clienteRepository.cadastro(cliente);
    }

    public Cliente buscaClientePorCPF(String cpf) {
        return this.clienteRepository.buscaCPF(new CPF(cpf));
    }

}
