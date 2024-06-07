package com.jesus.client.client_practica_app.services;

import com.jesus.client.client_practica_app.entities.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

public interface CarService {

    ResponseEntity<Iterable<Car>> findAll();

    ResponseEntity<Car> findById(Long id);

    ResponseEntity<Iterable<Car>> findByMarca(String marca);

    ResponseEntity<Car> deleteById(Long id);

    ResponseEntity<Car> createCar(Car car, UriComponentsBuilder ucb);

    ResponseEntity<Car> updateCar(Long id, Car car);
}
