package se.lexicon.erik.jpa_workshop.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProductOrderTest {
	
	private ProductOrder testObject;
	private OrderItem item1, item2;
	private AppUser user;
	
	private List<OrderItem> items(){
		List<OrderItem> items = new ArrayList<>();
		Product product = new Product(1, "Test product", BigDecimal.valueOf(10));
		Product product2 = new Product(2, "Test product 2", BigDecimal.valueOf(150));
		item1 = new OrderItem(1,5,product,null);
		item2 = new OrderItem(2,3,product2, null);
		items.add(item1);
		items.add(item2);
		return items;
	}
	
	@Before
	public void setup() {
		user = new AppUser(1, "Test", "Testsson", "test@test.com");
		LocalDate date = LocalDate.parse("2019-09-30");
		LocalTime time = LocalTime.parse("08:35");
		testObject = new ProductOrder(1,LocalDateTime.of(date, time), items(), user);		
	}
	
	@Test
	public void testObject_successfully_created() {
		assertEquals(1, testObject.getOrderId());
		assertEquals(LocalDate.parse("2019-09-30"), testObject.getOrderDate());
		assertEquals(LocalTime.parse("08:35"), testObject.getOrderTime());
		assertEquals(2, testObject.getOrderContent().size());
		assertEquals(user, testObject.getCustomer());
		assertTrue(
				(testObject.getOrderContent().contains(item1) && item1.getOrder().equals(testObject))
				&&
				(testObject.getOrderContent().contains(item2) && item2.getOrder().equals(testObject))
			);
	}
	
	@Test
	public void testObject_getProductOrderPriceTotal_returns_500_00() {
		BigDecimal expectedTotal = BigDecimal.valueOf(500.00).setScale(2, RoundingMode.HALF_EVEN);
		
		assertEquals(expectedTotal, testObject.getProductOrderPriceTotal());
	}
	
	@Test
	public void setOrderContent_null_clears_all_references() {
		testObject.setOrderContent(null);
		assertNull(testObject.getOrderContent());
		assertNull(item1.getOrder());
		assertNull(item2.getOrder());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalid_creation_of_ProductOrder_throws_Exception() {
		new ProductOrder(2, LocalDateTime.now(), null, user);
	}	
		
	@Test
	public void given_OrderItem_successfully_adds_it() {
		Product itemContent = new Product(3, "USB cable", BigDecimal.valueOf(40));
		OrderItem orderItem = new OrderItem(3, 5, itemContent, null);
		
		testObject.addOrderItem(orderItem);
		
		assertEquals(3, testObject.getOrderContent().size());
		assertTrue(testObject.getOrderContent().contains(orderItem));
		assertTrue(orderItem.getOrder().equals(testObject));		
	}
	
	@Test
	public void given_OrderItem_successfully_removes_it() {		
		testObject.removeOrderItem(item1);
		assertEquals(1, testObject.getOrderContent().size());
		assertNull(item1.getOrder());
		assertFalse(testObject.getOrderContent().contains(item1));
	}
	
	@Test
	public void given_copy_equals_true() {
		AppUser user = new AppUser(1, "Test", "Testsson", "test@test.com");
		LocalDate date = LocalDate.parse("2019-09-30");
		LocalTime time = LocalTime.parse("08:35");
		ProductOrder copy = new ProductOrder(1,LocalDateTime.of(date, time), items(), user);
		
		assertTrue(copy.equals(testObject));
		assertEquals(copy.hashCode(), testObject.hashCode());
		
	}
	
	@Test
	public void toString_contains_correct_information() {
		String toString = testObject.toString();
		assertTrue(
			toString.contains("1") &&
			toString.contains("2019-09-30") &&
			toString.contains("08:35")
			
		);
	}
}
