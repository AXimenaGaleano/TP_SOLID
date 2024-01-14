package com.example.pokedex.services;

import com.example.pokedex.models.Pokemon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDatabaseService implements PokemonService {

    private static final String DATABASE_URL = "jdbc:sqlite:../sujet_TP/ressources/pokemondatabase.sqlite";


    @Override
    public Pokemon getPokemonById(int pokemonId) {
        String query = "SELECT * FROM pokemons WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, pokemonId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Procesar el resultado y convertirlo en un objeto Pokemon
                return parseDatabaseResult(resultSet);
            } else {
                System.out.println("No se encontró el Pokémon en la base de datos local.");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener datos del Pokémon desde la base de datos local: " + e.getMessage());
        }

        return null;
    }

    private Pokemon parseDatabaseResult(ResultSet resultSet) throws SQLException {
        // Extraer datos de las columnas del resultado
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("name");
        String description = resultSet.getString("description");
        double altura = resultSet.getDouble("height");
        double peso = resultSet.getDouble("weight");

        // Crear y retornar un objeto Pokemon con los datos extraídos
        return new Pokemon(nombre, description, altura, peso);
    }
}
