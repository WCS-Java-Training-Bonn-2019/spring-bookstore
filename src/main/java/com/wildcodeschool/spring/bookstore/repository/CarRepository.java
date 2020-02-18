package com.wildcodeschool.spring.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.spring.bookstore.entity.carpool.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
