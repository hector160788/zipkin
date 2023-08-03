package com.car.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.app.entity.CarEntity;
import com.car.app.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository repository;

	public List<CarEntity> getAllCar() {
		return repository.findAll();
	}

	public CarEntity getCarById(int id) {
		return repository.findById(id).orElse(null);
	}

	public CarEntity saveCar(CarEntity carro) {
		repository.save(carro);
		return carro;
	}
	
	public List<CarEntity> getCarByUserId(int userid){
		
		return repository.findByUserId(userid);
	} 

}
