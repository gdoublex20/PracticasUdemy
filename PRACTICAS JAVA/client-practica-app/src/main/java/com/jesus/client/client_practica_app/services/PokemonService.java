package com.jesus.client.client_practica_app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {

    private final RestTemplate restTemplate;

    public PokemonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerPokemon(String param) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + param;
        return restTemplate.getForObject(url, String.class);
    }
}
