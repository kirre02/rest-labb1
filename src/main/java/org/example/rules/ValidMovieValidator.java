package org.example.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.dto.CreateMovie;
import org.example.exceptions.ValidationException;

public class ValidMovieValidator implements ConstraintValidator<ValidMovie, CreateMovie> {

    @Override
    public boolean isValid(CreateMovie createMovie, ConstraintValidatorContext context) {
        if (createMovie == null) {
            return false;
        }

        String title = createMovie.title();
        int duration = createMovie.duration();

// Validate that the title starts with an uppercase letter
        if (title == null || title.isEmpty() || !Character.isUpperCase(title.charAt(0))) {
            throw new ValidationException("Title must start with an uppercase letter");
        }

        if (duration == title.length()) {
            throw new ValidationException("Duration cannot be equal to the title length");
        }

        return true;
    }
}


