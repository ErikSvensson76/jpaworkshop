package se.lexicon.erik.jpa_workshop.entity;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
	
	private Product testObject;
	
	@Before
	public void setup() {
		testObject = new Product(1, "Spam", BigDecimal.valueOf(10.90));
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(1, testObject.getProductId());
		assertEquals("Spam", testObject.getProductName());
		assertEquals(BigDecimal.valueOf(10.90).setScale(2), testObject.getPrice());
	}
	
	@Test
	public void test_equals_and_hashCode() {
		Product copy = new Product(1, "Spam", BigDecimal.valueOf(10.90));
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
	}
	
	@Test
	public void test_toString_contains_correct_information() {
		String toString = testObject.toString();
		assertTrue(
				toString.contains("1") &&
				toString.contains("Spam") &&
				toString.contains("10.90")
		);		
	}
	
	

}
