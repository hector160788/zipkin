package com.bike.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bike.app.entity.BikeEntity;
import com.bike.app.service.BikeService;

@RestController
@RequestMapping(value = "/bike")
public class BikeController {
	
	@Autowired
	private BikeService bikeService;
	
	@GetMapping
	public ResponseEntity<List<BikeEntity>> getAllBike(){
		List<BikeEntity> listabikes = bikeService.getAllBike();
		if(listabikes.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listabikes);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BikeEntity> getBikeById(@PathVariable(value = "id") int id){
		BikeEntity bike = bikeService.getBikeById(id);
		if(bike == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bike);
	}
	
	@PostMapping
	public ResponseEntity<BikeEntity> saveBike(@RequestBody BikeEntity bike){
		BikeEntity bikeresponse = bikeService.saveBike(bike);
		if(bikeresponse == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(bikeresponse);
	}
	
	@GetMapping(value = "/byuser/{userid}")
	public ResponseEntity<List<BikeEntity>> getBikeByUserID(@PathVariable(value = "userid") int userid){
		List<BikeEntity> listabike = bikeService.getBikeByUserId(userid);
//		if(listabike.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
		return ResponseEntity.ok(listabike);
	}

}
