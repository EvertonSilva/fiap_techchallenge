package br.com.edu.fiap.techchallengelanchonete.validation;

import br.com.edu.fiap.techchallengelanchonete.validation.annotation.ValidCliente;
import br.com.edu.fiap.techchallengelanchonete.domain.Cliente.Cliente;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ClienteValidator implements ConstraintValidator<ValidCliente, Cliente> {

    @Override
    public void initialize(ValidCliente constraintAnnotation) {
    }

    @Override
    public boolean isValid(Cliente cliente, ConstraintValidatorContext context) {
        if (cliente == null) {
            return false;
        }

        boolean isNomeValid = isNomeValid(cliente.getNome());
        boolean isEmailValid = isEmailValid(cliente.getEmail());
        boolean isCPFValid = isCPFValid(cliente.getCpf(), context);

        // Valida somente se todos os campos do cliente estiverem corretos
        return isNomeValid && isEmailValid && isCPFValid;
    }

    private boolean isNomeValid(Nome nome) {
        // Implemente as regras de validação para o nome aqui
        // Retorna true se o nome for válido, caso contrário, retorna false.
        return true; // Substitua por sua lógica de validação do nome
    }

    private boolean isEmailValid(Email email) {
        // Implemente as regras de validação para o email aqui
        // Retorna true se o email for válido, caso contrário, retorna false.
        return true; // Substitua por sua lógica de validação do email
    }

    private boolean isCPFValid(CPF cpf, ConstraintValidatorContext context) {
        CPFValidator cpfValidator = new CPFValidator();
        return cpfValidator.isValid(cpf, context);
    }
}
