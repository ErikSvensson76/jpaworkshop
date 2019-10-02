package se.lexicon.erik.jpa_workshop.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.jpa_workshop.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class ProductRepoTest {
	
	@Autowired private ProductRepo testObject;
	
	private List<Product> data(){
		return new ArrayList<>(Arrays.asList(
					new Product("JPA Book", BigDecimal.valueOf(399)),
					new Product("JPA Book", BigDecimal.valueOf(799)),
					new Product("Odyssey", BigDecimal.valueOf(99.9))
				)
			);
	}	
	
	@Before
	public void setup() {
		testObject.saveAll(data());		
	}
	
	@Test
	public void given_JPA_findByProductNameStartsWithIgnoreCase_should_return_List_size_2() {
		String query = "jpa";
		
		List<Product> result = testObject.findByProductNameStartsWithIgnoreCase(query);
		
		assertEquals(2, result.size());
		assertTrue(result.stream()
				.allMatch(prod -> prod.getProductName().toLowerCase().startsWith("jpa")));				
	}

}
