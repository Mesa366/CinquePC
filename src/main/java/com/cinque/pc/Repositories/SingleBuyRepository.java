package com.cinque.pc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.SingleBuy;

@Repository
public interface SingleBuyRepository extends JpaRepository<SingleBuy, String>{

}
