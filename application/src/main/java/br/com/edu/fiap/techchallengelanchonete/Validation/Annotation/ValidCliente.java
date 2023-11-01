package br.com.edu.fiap.techchallengelanchonete.Validation.Annotation;

import br.com.edu.fiap.techchallengelanchonete.Validation.ClienteValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ClienteValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCliente {
    String message() default "Cliente inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}