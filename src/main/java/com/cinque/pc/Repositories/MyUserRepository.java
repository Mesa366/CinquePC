package com.cinque.pc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String>{
	
}
