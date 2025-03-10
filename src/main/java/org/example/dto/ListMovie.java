package org.example.dto;

import org.example.Entity.Movie;

import java.util.List;

public record ListMovie(List<MovieResponse> data) {}
