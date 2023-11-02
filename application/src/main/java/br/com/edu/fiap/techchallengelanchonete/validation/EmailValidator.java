package br.com.edu.fiap.techchallengelanchonete.validation;

import br.com.edu.fiap.techchallengelanchonete.validation.annotation.ValidEmail;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.Email;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, Email> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(Email email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }

        String emailStr = email.getValor();

        if (emailStr == null) {
            return false;
        }

        // Verifique o formato correto de e-mail usando uma expressão regular
        if (!emailStr.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return false;
        }

        // Separe o domínio do e-mail para verificar seu formato
        String[] parts = emailStr.split("@");
//        if (parts.length != 2 || !parts[1].matches("^[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$")) {
//            return false;
//        }

        // Verifique se o nome do e-mail tem mais de 3 caracteres
        String nome = parts[0];
        if (nome.length() <= 3) {
            return false;
        }

        // Verifique se o nome do e-mail não contém apenas números
        if (nome.matches("^[0-9]+$")) {
            return false;
        }

        // Se todas as condições acima forem atendidas, o e-mail é considerado válido.
        return true;
    }
}
