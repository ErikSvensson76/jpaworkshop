package se.lexicon.erik.jpa_workshop.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.erik.jpa_workshop.entity.AppUser;
import se.lexicon.erik.jpa_workshop.entity.OrderItem;
import se.lexicon.erik.jpa_workshop.entity.Product;
import se.lexicon.erik.jpa_workshop.entity.ProductOrder;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestEntityManager
@Transactional
public class ProductOrderRepoTest {
	
	@Autowired private ProductOrderRepo testObject;
	@Autowired private TestEntityManager em;
	
	private int appUserId;
	private int productId;	
	private ProductOrder order2;
	private OrderItem item2;
	
	@Before
	public void setup() {
		AppUser user1 = em.persistAndFlush(new AppUser("Test", "Testsson", "test@test.com"));
		appUserId = user1.getUserId();
		Product product1 = em.persistAndFlush(new Product("TestProduct 1", BigDecimal.valueOf(10)));
		productId = product1.getProductId();
		Product product2 = em.persistAndFlush(new Product("TestProduct 2", BigDecimal.valueOf(20)));
		OrderItem item1 = em.persistAndFlush(new OrderItem(5, product1));
		item2 = em.persistAndFlush(new OrderItem(3, product1));
		OrderItem item3 = em.persistAndFlush(new OrderItem(2, product2));
		
		ProductOrder order1 = new ProductOrder(LocalDateTime.of(LocalDate.parse("2019-10-02"), LocalTime.parse("09:30")), user1);
		order1.addOrderItem(item1);
		order1.addOrderItem(item3);
		
		ProductOrder order2 = new ProductOrder(LocalDateTime.of(LocalDate.parse("2019-10-01"), LocalTime.parse("10:30")), user1);
		order2.addOrderItem(item2);	
		order1 = testObject.save(order1);
		this.order2 = testObject.save(order2);
	}
	
	@Test
	public void given_2019_10_02_should_return_list_size_1() {
		List<ProductOrder> result = testObject.findByOrderDate(LocalDate.parse("2019-10-02"));
		
		assertEquals(1, result.size());
	}
	
	@Test
	public void given_productId_return_list_size_2() {
		List<ProductOrder> result = testObject.findByOrderContentProductProductId(productId);
		assertEquals(2, result.size());
	}
	
	@Test
	public void given_string_testproduct_1_should_return_list_size_2() {
		List<ProductOrder> result = testObject.findByOrderContentProductProductNameIgnoreCase("testproduct 1");
		assertEquals(2, result.size());
	}
	
	@Test
	public void given_appUserId_should_return_list_of_size_2() {
		List<ProductOrder> result = testObject.findByCustomerUserId(appUserId);
		
		assertEquals(2, result.size());
	}
	
	@Test
	public void removing_item2_from_order2_should_remove_item2_from_database() {
		int itemId = item2.getItemId();
		order2.removeOrderItem(item2);		
		em.flush(); //commit changes triggering orphan removal
		assertNull(em.find(OrderItem.class, itemId));		
	}
}
