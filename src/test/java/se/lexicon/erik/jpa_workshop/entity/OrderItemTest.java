package se.lexicon.erik.jpa_workshop.entity;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderItemTest {
	
	private OrderItem testObject;
	private Product product;
	private ProductOrder order;
	
	@Before
	public void setup() {
		product = new Product(1,"Spam",BigDecimal.valueOf(10));
		order = new ProductOrder();
		testObject = new OrderItem(1, 5, product, order);
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(1, testObject.getItemId());
		assertEquals(5, testObject.getQuantity());
		assertEquals(product, testObject.getProduct());
		assertEquals(order, testObject.getOrder());
	}
	
	@Test
	public void getItemPrice_return_50_00() {
		BigDecimal expected = BigDecimal.valueOf(50).setScale(2);
		
		assertEquals(expected, testObject.getItemPrice());
	}
	
	@Test
	public void given_copy_equals_testObject() {
		Product productCopy = new Product(1,"Spam",BigDecimal.valueOf(10));
		ProductOrder orderCopy = new ProductOrder();
		OrderItem copy = new OrderItem(1, 5, productCopy, orderCopy);
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void toString_contains_expected_data() {
		String string = testObject.toString();
		assertTrue(
			string.contains("1") &&
			string.contains("5") &&
			string.contains(product.toString())			
		);
	}

}
