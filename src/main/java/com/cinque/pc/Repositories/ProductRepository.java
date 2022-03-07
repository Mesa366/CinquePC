package com.cinque.pc.Repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinque.pc.Entities.MyUser;
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

	public List<Product> getByName(String name);
	
	@Query("SELECT a FROM Product a WHERE a.userWishList = :userWishList")
	public List<Product> getWishListProductsByUserWishList(@Param("userWishList") MyUser userWishList);	
	
	//VER COMO PASAR LA QUERY NATIVA A UNA DE TIPO JPA HIBERNATE:
	 // @Query(value = "SELECT * FROM Product WHERE Product.name LIKE %:q%", nativeQuery = true)
	  //  List<Product> findByTitle(@Param("q")String q);
	  
	  @Query("SELECT a FROM Product a WHERE a.name = :keyword") //agregar OR a.seller
		public List<Product> findByKeyword(@Param("keyword") String keyword);
}
