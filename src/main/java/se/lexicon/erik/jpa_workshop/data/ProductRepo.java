package se.lexicon.erik.jpa_workshop.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.jpa_workshop.entity.Product;

public interface ProductRepo extends CrudRepository<Product, Integer>{
	
	List<Product> findByProductNameStartsWithIgnoreCase(String productName);

}
