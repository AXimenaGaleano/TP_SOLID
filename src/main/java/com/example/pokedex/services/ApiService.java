package com.example.pokedex.services;

import com.example.pokedex.models.Pokemon;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService implements PokemonService {
    // URL base de la API
    private static final String API_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    public Pokemon getPokemonById(int pokemonId) {
        // Lógica específica para obtener datos desde la API
        String apiUrl = API_BASE_URL + pokemonId;

        try {
            String apiResponse = makeApiRequest(apiUrl);

            // Deserializa la respuesta JSON a un objeto Pokemon utilizando Gson
            Gson gson = new Gson();
            Pokemon pokemon = gson.fromJson(apiResponse, Pokemon.class);

            return pokemon;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Maneja las excepciones de manera apropiada en tu aplicación
            return null;
        }
    }

    private String makeApiRequest(String apiUrl) throws IOException, InterruptedException {
        // Realiza la solicitud HTTP utilizando HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Verifica si la solicitud fue exitosa (código de estado 200)
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Error en la solicitud HTTP: " + response.statusCode());
        }
    }
}
