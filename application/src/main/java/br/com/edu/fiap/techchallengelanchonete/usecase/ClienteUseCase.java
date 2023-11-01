package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;

public class ClienteUseCase {
    private IClientePersistence clienteRepository;

    public ClienteUseCase(IClientePersistence clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteNulo semIdentificacaoCliente() {
        return new ClienteNulo();
    }

    public Cliente salvaCliente(Cliente cliente) {
        return this.clienteRepository.cadastro(cliente);
    }

    // FIXME
//    public Cliente autenticaCliente(String cpf, String email) {
//        var clienteExiste = this.clienteRepository.findBycpfAndEmail(cpf, email);
//        if (clienteExiste.isPresent())
//            return this.clienteAdapter.toDomain(clienteExiste.get());
//        else
//            return null;
//    }

    public Cliente buscaClientePorCPF(String cpf) {
        return this.clienteRepository.buscaCPF(new CPF(cpf));
    }

    // FIXME
//    private Cliente clienteExiste(Cliente cliente){
//        var model = this.clienteAdapter.toModel(cliente);
//
//        var clienteExiste = this.clienteRepository.findBycpf(model.getCpf());
//        if (clienteExiste.isPresent())
//            return this.clienteAdapter.toDomain(clienteExiste.get());
//        else{
//            model = this.clienteRepository.save(model);
//            return this.clienteAdapter.toDomain(model);
//        }
//    }

}
