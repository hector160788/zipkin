package com.car.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.app.entity.CarEntity;
import com.car.app.service.CarService;

@RestController
@RequestMapping(value = "/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@GetMapping
	public ResponseEntity<List<CarEntity>> getAllCar(){
		List<CarEntity> listacarros = carService.getAllCar();
		if(listacarros.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listacarros);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CarEntity> getCarById(@PathVariable(value = "id") int id){
		CarEntity carro = carService.getCarById(id);
		if(carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carro);
	}
	
	@PostMapping
	public ResponseEntity<CarEntity> saveCar(@RequestBody CarEntity carro){
		CarEntity carroresponse = carService.saveCar(carro);
		if(carro == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carroresponse);
	}
	
	@GetMapping(value = "/byuser/{userId}")
	public ResponseEntity<List<CarEntity>> getCarByUserID(@PathVariable(value = "userId") int userid){
		List<CarEntity> listacarros = carService.getCarByUserId(userid);
//		if(listacarros.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
		return ResponseEntity.ok(listacarros);
	}
	
	

}
