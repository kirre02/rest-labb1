package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.rules.ValidMovie;

@ValidMovie
public record CreateMovie(@NotBlank @NotNull String title,
                          @NotBlank @NotNull String director,
                          @Positive(message = "must be positive") int duration,
                          @Positive(message = "must be positive") int year
                          ) {}
