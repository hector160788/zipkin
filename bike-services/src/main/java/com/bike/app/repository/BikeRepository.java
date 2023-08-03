package com.bike.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bike.app.entity.BikeEntity;

public interface BikeRepository extends JpaRepository<BikeEntity, Integer> {
	
	public List<BikeEntity> findByUserId(int userid);

}
