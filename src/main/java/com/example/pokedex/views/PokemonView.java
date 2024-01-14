package com.example.pokedex.views;

import com.example.pokedex.models.Pokemon;
import com.example.pokedex.utilities.MultipleFormatGenerator;

public class PokemonView implements MultipleFormatGenerator {

    @Override
    public String generateHumanReadableText(Pokemon pokemon) {
        StringBuilder textRepresentation = new StringBuilder();
        textRepresentation.append("Name: ").append(pokemon.getName()).append("\n");
        textRepresentation.append("Type: ").append(pokemon.getType()).append("\n");
        textRepresentation.append("Height: ").append(pokemon.getHeight()).append(" m\n");
        textRepresentation.append("Weight: ").append(pokemon.getWeight()).append(" kg\n");
        // Agregar más detalles según sea necesario
        return textRepresentation.toString();
    }

    @Override
    public String generateHTML(Pokemon pokemon) {
        StringBuilder htmlRepresentation = new StringBuilder();
        htmlRepresentation.append("<h1>").append(pokemon.getName()).append("</h1>");
        htmlRepresentation.append("<p><b>Type:</b> ").append(pokemon.getType()).append("</p>");
        htmlRepresentation.append("<p><b>Height:</b> ").append(pokemon.getHeight()).append(" m</p>");
        htmlRepresentation.append("<p><b>Weight:</b> ").append(pokemon.getWeight()).append(" kg</p>");
        return htmlRepresentation.toString();
    }

    @Override
    public String generateCSV(Pokemon pokemon) {
        StringBuilder csvRepresentation = new StringBuilder();
        csvRepresentation.append("Name, Type, Height, Weight\n");
        csvRepresentation.append(pokemon.getName()).append(", ");
        csvRepresentation.append(pokemon.getType()).append(", ");
        csvRepresentation.append(pokemon.getHeight()).append(", ");
        csvRepresentation.append(pokemon.getWeight()).append("\n");
        // Agregar más detalles según sea necesario
        return csvRepresentation.toString();
    }
}