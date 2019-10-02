package se.lexicon.erik.jpa_workshop.data;

import org.springframework.data.repository.CrudRepository;

import se.lexicon.erik.jpa_workshop.entity.OrderItem;

public interface OrderItemRepo extends CrudRepository<OrderItem, Integer>{

}
