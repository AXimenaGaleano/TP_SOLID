package com.example.pokedex.utilities;

import com.example.pokedex.models.Pokemon;

public interface MultipleFormatGenerator {
    public String generateHTML(Pokemon pokemon);
    public String generateCSV(Pokemon pokemon);
    public String generateHumanReadableText(Pokemon pokemon);
}
