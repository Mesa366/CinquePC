package com.cinque.pc.Repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.MyUser;
import com.cinque.pc.Entities.Product;
import com.cinque.pc.Entities.SingleBuy;

@Repository
public interface SingleBuyRepository extends JpaRepository<SingleBuy, String>{

	@Query("SELECT a FROM SingleBuy a WHERE a.userShoppingCart = :userShoppingCart")
	public List<SingleBuy> getShoppingCartByUserShoppingCart(@Param("userShoppingCart") MyUser userShoppingCart);
	
	@Query("SELECT a FROM SingleBuy a WHERE a.userSellingProducts = :userSellingProducts")
	public List<SingleBuy> getSellingProductsByUserSellingProducts(@Param("userSellingProducts") MyUser userSellingProducts);	
	
	@Query("SELECT a FROM SingleBuy a WHERE a.userShoppingHistory = :userShoppingHistory")
	public List<SingleBuy> getShoppingHistoryByUserShoppingHistory(@Param("userShoppingHistory") MyUser userShoppingHistory);
	
}
