package se.lexicon.erik.jpa_workshop.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*
 * 5.	Create OrderItem class in entity with the following fields:
		a.	Id, quantity, product, productOrder (Need to create a new class in same package called ProductOrder)
		b.	Add a method that calculate the price with help from product and quantity.
		c.	Test and commit

 */

public class OrderItem {
	
	private int itemId;
	private int quantity;
	private Product product;
	private ProductOrder order;
	
	public OrderItem(int itemId, int quantity, Product product, ProductOrder order) {
		this.itemId = itemId;
		setQuantity(quantity);
		setProduct(product);
		setOrder(order);
	}

	public OrderItem(int quantity, Product product) {
		this(0, quantity, product, null);		
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductOrder getOrder() {
		return order;
	}

	protected void setOrder(ProductOrder order) {
		this.order = order;
	}

	public int getItemId() {
		return itemId;
	}
	
	public BigDecimal getItemPrice() {
		return product.getPrice()
				.multiply(BigDecimal.valueOf(quantity))
				.setScale(2);
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return itemId == other.itemId && quantity == other.quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [itemId=");
		builder.append(itemId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", product=");
		builder.append(product);
		builder.append("]");
		return builder.toString();
	}
}
