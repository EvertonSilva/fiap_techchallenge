package br.com.edu.fiap.techchallengelanchonete.usecase;

import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.ClienteNulo;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Id;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import br.com.edu.fiap.techchallengelanchonete.exception.ApplicationException;
import br.com.edu.fiap.techchallengelanchonete.infrastructure.IClientePersistence;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ClienteUseCase {
    private IClientePersistence clienteRepository;

    public ClienteUseCase(IClientePersistence clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvaCliente(Cliente cliente) {
        Cliente clienteExiste = this.clienteRepository.buscaCPF(cliente.getCpf());
        if (clienteExiste.getId() != null)
            throw new ApplicationException("Cliente já existe!");

        return this.clienteRepository.cadastro(cliente);
    }

    public Cliente buscaClientePorCPF(String cpf) {
        return this.clienteRepository.buscaCPF(new CPF(cpf));
    }

//    private void teste()
//    {
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//
//        Cliente cliente = new Cliente();
//        cliente.setNome(new Nome("Cliente Teste"));
//
//        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
//
//        if (violations.isEmpty())
//            System.out.println("Cliente é válido.");
//    }

}
