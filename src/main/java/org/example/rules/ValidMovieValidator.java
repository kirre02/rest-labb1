package org.example.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.dto.CreateMovie;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {
    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext constraintValidatorContext) {
        if (Character.isUpperCase(createMovie.title().charAt(0)) &&
                createMovie.duration() != createMovie.title().length()) {
            return true;
        }
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("Length and title doesn't match")
                .addPropertyNode("title").addConstraintViolation();
        return false;
    }
}
