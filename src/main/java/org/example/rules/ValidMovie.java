package org.example.rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.ws.rs.ConstrainedTo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidMovieValidator.class})
public @interface ValidMovie {

    String message() default "Invalid movie";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
