package br.com.edu.fiap.techchallengelanchonete.validation;

import br.com.edu.fiap.techchallengelanchonete.validation.annotation.ValidCPF;
import br.com.edu.fiap.techchallengelanchonete.domain.valueobject.CPF;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<ValidCPF, CPF> {

    @Override
    public void initialize(ValidCPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(CPF cpf, ConstraintValidatorContext context) {
        if (cpf == null) {
            return false;
        }

        String cpfStr = cpf.getValor();

        if (cpfStr == null /*|| !cpfStr.matches("\d{11}")*/) {
            return false;
        }

        // Verifique se o CPF não possui todos os números iguais
        if (isAllDigitsEqual(cpfStr)) {
            return false;
        }

        // Verifique o dígito verificador
        if (!isCPFFormatValid(cpfStr)) {
            return false;
        }

        // Se todas as condições acima forem atendidas, o CPF é considerado válido.
        return true;
    }

    private boolean isAllDigitsEqual(String cpf) {
        char firstDigit = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != firstDigit) {
                return false;
            }
        }
        return true;
    }

    private boolean isCPFFormatValid(String cpf) {
        int sum1 = 0;
        int sum2 = 0;
        int cpfLength = 11;

        for (int i = 0; i < cpfLength - 2; i++) {
            int digit = Character.getNumericValue(cpf.charAt(i));
            sum1 += digit * (cpfLength - i);
            sum2 += digit * (cpfLength - i + 1);
        }

        int mod1 = (sum1 % 11) < 2 ? 0 : 11 - (sum1 % 11);
        int mod2 = (sum2 % 11) < 2 ? 0 : 11 - (sum2 % 11);

        return (Character.getNumericValue(cpf.charAt(9)) == mod1) && (Character.getNumericValue(cpf.charAt(10)) == mod2);
    }
}