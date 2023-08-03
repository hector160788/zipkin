package com.car.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.app.entity.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
	
	public List<CarEntity> findByUserId(int userid);

}
