package com.jesus.client.client_practica_app.services;

import com.jesus.client.client_practica_app.Repositories.CarRepository;
import com.jesus.client.client_practica_app.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImp(CarRepository CarRepository){
        this.carRepository = CarRepository;
    }

    @Override
    public ResponseEntity<Iterable<Car>> findAll() {
        Iterable<Car> Cars = carRepository.findAll();
        return new ResponseEntity<>(Cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> findById(Long id) {
        return carRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Iterable<Car>> findByMarca(String marca) {
        List<Car> Cars = carRepository.findByMarca(marca);
        return new ResponseEntity<>(Cars, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Car> createCar(Car Car, UriComponentsBuilder ucb) {
        Car savedCar = carRepository.save(Car);
        URI locationOfNewCard = ucb
                .path("catalogos/carros/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCard).body(savedCar);

    }

    @Override
    public ResponseEntity<Car> deleteById(Long id) {
        if (!carRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            carRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Car> updateCar(Long id, Car updateCar) {
        ResponseEntity<Car> car = findById(id);
        if(car != null){
            Car updatedCar = new Car(id, updateCar.getNombre(), updateCar.getMarca(), updateCar.getModelo());
            carRepository.save(updatedCar);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }
    
}
