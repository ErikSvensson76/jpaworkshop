package se.lexicon.erik.jpa_workshop.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	private LocalDate orderDate;
	private LocalTime orderTime;
	
	@OneToMany(
			fetch = FetchType.EAGER,
			cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
			mappedBy = "order",
			orphanRemoval = true
	)
	private List<OrderItem> orderContent;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id")
	private AppUser customer;
	
	public ProductOrder(int orderId, LocalDateTime timeStamp, List<OrderItem> orderContent, AppUser customer) {
		if(orderContent == null) throw new IllegalArgumentException("Ordercontent was " + orderContent);
		this.orderId = orderId;
		setOrderDate(timeStamp.toLocalDate());
		setOrderTime(timeStamp.toLocalTime());
		setOrderContent(orderContent);
		this.orderContent.forEach(item -> item.setOrder(this));
		setCustomer(customer);
		
	}

	public ProductOrder(LocalDateTime timeStamp, AppUser customer) {
		this(0, timeStamp, new ArrayList<>(), customer);		
	}		

	public ProductOrder() {
	}	

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
	}

	public List<OrderItem> getOrderContent() {
		return orderContent;
	}	

	public AppUser getCustomer() {
		return customer;
	}

	public void setCustomer(AppUser customer) {
		this.customer = customer;
	}

	public void setOrderContent(List<OrderItem> orderContent) {
		if(orderContent == null) {
			this.orderContent.forEach(item -> item.setOrder(null));
		}
		this.orderContent = orderContent;		
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void addOrderItem(OrderItem item) {
		if(orderContent == null) orderContent = new ArrayList<>();
		if(!orderContent.contains(item)) {
			orderContent.add(item);
			item.setOrder(this);
		}
	}
	
	public void removeOrderItem(OrderItem item) {
		if(orderContent == null) orderContent = new ArrayList<>();
		if(orderContent.contains(item)) {
			item.setOrder(null);
			orderContent.remove(item);
		}		
	}	

	@Override
	public int hashCode() {
		return Objects.hash(orderDate, orderId, orderTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrder other = (ProductOrder) obj;
		return Objects.equals(orderDate, other.orderDate) && orderId == other.orderId
				&& Objects.equals(orderTime, other.orderTime);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductOrder [orderId=");
		builder.append(orderId);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderTime=");
		builder.append(orderTime);
		builder.append("]");
		return builder.toString();
	}	
}
