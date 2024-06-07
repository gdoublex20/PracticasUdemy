package com.jesus.client.client_practica_app.controllers;

import com.jesus.client.client_practica_app.entities.Car;
import com.jesus.client.client_practica_app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/catalogos/carros")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<Car>> findAll() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping("/marca/{marca}")
    public ResponseEntity<Iterable<Car>> findByMarca(@PathVariable String marca) {
        return carService.findByMarca(marca);
    }

    @PostMapping("/create")
    private ResponseEntity<Car> createCar(@RequestBody Car newCar, UriComponentsBuilder ucb) {
        return carService.createCar(newCar, ucb);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Car> deleteById(@PathVariable Long id) {
        return carService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car updateCar){
        return carService.updateCar(id, updateCar);
    }
}
