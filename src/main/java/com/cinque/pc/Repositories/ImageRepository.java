package com.cinque.pc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String>{
	
	
	
}
