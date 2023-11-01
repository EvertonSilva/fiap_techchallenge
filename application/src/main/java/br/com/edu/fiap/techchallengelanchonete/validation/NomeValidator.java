package br.com.edu.fiap.techchallengelanchonete.validation;

import br.com.edu.fiap.techchallengelanchonete.validation.annotation.ValidNome;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Nome;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NomeValidator implements ConstraintValidator<ValidNome, Nome> {

    @Override
    public void initialize(ValidNome constraintAnnotation) {
    }

    @Override
    public boolean isValid(Nome nome, ConstraintValidatorContext context) {
        if (nome == null) {
            return false;
        }

        String nomeStr = nome.getValor();

        if (nomeStr == null) {
            return false;
        }

        // Verifique se o nome contém 0 caracteres
        if (nomeStr.isEmpty()) {
            return false;
        }

        // Verifique se o nome contém apenas números
        if (nomeStr.matches("^[0-9]+$")) {
            return false;
        }

        // Verifique se o nome tem mais de 100 caracteres
        if (nomeStr.length() > 100) {
            return false;
        }

        // Se nenhuma das condições acima for atendida, o nome é considerado válido.
        return true;
    }
}