package com.pubkart.order.test;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pubkart.order.model.Order;
import com.pubkart.order.repository.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private OrderRepository orderRepository;

	@Test
	public void testWhenReceiveCartCreateOrder() {
		assertNotNull(orderRepository.save(new Order()));
	}

}
