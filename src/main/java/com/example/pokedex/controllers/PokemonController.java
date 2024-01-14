package com.example.pokedex.controllers;

import com.google.gson.Gson;
import com.example.pokedex.models.Pokemon;
import com.example.pokedex.services.PokemonService;

public class PokemonController {
    private final PokemonService apiService;

    public PokemonController(PokemonService apiService) {
        this.apiService = apiService;
    }

    public Pokemon getPokemonById(int pokemonId) {
        return apiService.getPokemonById(pokemonId);
    }
}