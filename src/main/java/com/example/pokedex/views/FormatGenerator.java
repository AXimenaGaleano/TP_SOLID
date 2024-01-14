package com.example.pokedex.views;

import com.example.pokedex.models.Pokemon;

public interface FormatGenerator {
    String generateText(Pokemon pokemon);

    String generateHTML(Pokemon pokemon);

    String generateCSV(Pokemon pokemon);
}