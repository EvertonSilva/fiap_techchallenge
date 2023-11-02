package br.com.edu.fiap.techchallengelanchonete.validation.annotation;

import br.com.edu.fiap.techchallengelanchonete.validation.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {
    String message() default "E-mail inválido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
