package com.cinque.pc.Repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

	/*@Query("SELECT a FROM Product a WHERE a.buyer.id = :id")
	public List<Product> getProductsByBuyerId(@Param("id") String id);
	*/
	@Query("SELECT a FROM Product a WHERE a.seller.id = :id")
	public List<Product> getProductsBySellerId(@Param("id") String id);
	
	@Query("SELECT a FROM Product a WHERE a.category = :category")
	public List<Product> getProductsByCategory(@Param("category") String category);
	
}
