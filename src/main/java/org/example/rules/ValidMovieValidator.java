package org.example.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.dto.CreateMovie;


public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {
    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext context) {
        if (createMovie == null || createMovie.title() == null) {
            return true;
        }

        String title = createMovie.title();
        int duration = createMovie.duration();

        if (!Character.isUpperCase(title.charAt(0))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Title must start with an uppercase letter")
                    .addPropertyNode("title").addConstraintViolation();
            return false;
        }

        if (duration == title.length()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Duration cannot be equal to the title length")
                    .addPropertyNode("duration").addConstraintViolation();
            return false;
        }

        return true;
    }
}

