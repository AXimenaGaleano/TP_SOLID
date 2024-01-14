package com.example.pokedex.services;

import com.example.pokedex.models.Pokemon;

public interface PokemonService {
    Pokemon getPokemonById(int pokemonId);
}