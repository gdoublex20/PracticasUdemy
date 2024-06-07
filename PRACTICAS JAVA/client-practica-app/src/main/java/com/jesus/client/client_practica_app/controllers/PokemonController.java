package com.jesus.client.client_practica_app.controllers;

import com.jesus.client.client_practica_app.services.EncryptionServiceImp;
import com.jesus.client.client_practica_app.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {

    private PokemonService pokemonService;
    private EncryptionServiceImp encryptionService;

    @Autowired
    public PokemonController(PokemonService pokemonService, EncryptionServiceImp encryptionService) {
        this.pokemonService = pokemonService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/{name}")
    public String obtenerPokemon(@PathVariable String name) {
            return pokemonService.obtenerPokemon(name);
    }

    @GetMapping("/encrypted/{name}")
    public String obtenerEncriptado(@PathVariable String name) {
        try {
            String encryptedName = encryptionService.encrypt(name);
            return encryptedName;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al encriptar: " + e.getMessage();
        }
    }
}
