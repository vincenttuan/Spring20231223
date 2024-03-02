package com.mvc.psi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mvc.psi.model.po.Product;
import com.mvc.psi.model.vo.Inventory;
import com.mvc.psi.model.vo.ProductSales;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(nativeQuery = true, value = "select p.id, p.name, p.cost, p.price, "
			+ "(select sum(amount) from purchase_item where product_id = p.id limit 1) as amount1, "
			+ "(select sum(amount) from order_item where product_id = p.id limit 1) as amount2 "
			+ "from product p")
	List<Inventory> queryInventory();
	
	@Query(nativeQuery = true, value = "select p.id, p.name, p.cost, p.price, "
			+ "(select sum(amount) from purchase_item where product_id = p.id limit 1) as amount1, "
			+ "(select sum(amount) from order_item where product_id = p.id limit 1) as amount2 "
			+ "from product p where p.id=:id ")
	Inventory findInventoryById(Long id);
	
	// coalesce 合併
	// coalesce(sum(o.amount), 0) 若 sum(o.amount) 是 null 就以 0 來呈現
	@Query(nativeQuery = true, value="SELECT p.id, p.name, coalesce(sum(o.amount), 0) AS total "
			+ "FROM product p "
			+ "LEFT JOIN order_item o ON p.id = o.product_id "
			+ "GROUP BY p.id, p.name")
	List<ProductSales> queryProductSales();
}
