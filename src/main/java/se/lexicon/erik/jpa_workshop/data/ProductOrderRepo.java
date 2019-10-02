package se.lexicon.erik.jpa_workshop.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.jpa_workshop.entity.ProductOrder;

public interface ProductOrderRepo extends CrudRepository<ProductOrder, Integer>{
	
	List<ProductOrder> findByOrderDate(LocalDate orderDate);
	List<ProductOrder> findByOrderContentProductProductId(int productId);
	List<ProductOrder> findByOrderContentProductProductNameIgnoreCase(String productName);
	List<ProductOrder> findByCustomerUserId(int userId);
}
