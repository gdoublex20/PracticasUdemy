package com.jesus.client.client_practica_app.Repositories;

import com.jesus.client.client_practica_app.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);

    List<Car> findByMarca(String marca);
}
