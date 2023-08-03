package com.bike.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bike.app.entity.BikeEntity;
import com.bike.app.repository.BikeRepository;

@Service
public class BikeService {
	@Autowired
	private BikeRepository repository;

	public List<BikeEntity> getAllBike() {
		return repository.findAll();
	}

	public BikeEntity getBikeById(int id) {
		return repository.findById(id).orElse(null);
	}

	public BikeEntity saveBike(BikeEntity carro) {
		repository.save(carro);
		return carro;
	}
	
	public List<BikeEntity> getBikeByUserId(int userid){
		
		return repository.findByUserId(userid);
	} 

}
