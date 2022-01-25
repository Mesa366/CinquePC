package com.cinque.pc.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, String>{
	
	@Query("SELECT a FROM MyUser a WHERE a.email= :email")
	Optional<MyUser> getByEmail(@Param("email") String email);
	
}
