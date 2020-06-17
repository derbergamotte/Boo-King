package com.ak.dao;

import static org.junit.Assert.assertNotNull;

import com.ak.api.dao.IBookDao;
import com.ak.api.dao.IOrderDao;
import com.ak.api.dao.IUserDao;
import com.ak.entities.Order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages = { "com.ak" })
@EntityScan(basePackages = { "com.ak.entities" })
@EnableJpaRepositories(basePackages = { "com.ak.dao" })
@Transactional
public class TestOrderDao {

    @Autowired
    private IOrderDao orderDao;
    
    @Mock
    private IBookDao bookDao;
    
    @Mock
    private IUserDao userDao;

    @Before
    public void createOrder() {
	Order order = new Order();
	order.setBook(bookDao.getById(1L));
	order.setUser(userDao.getById(1L));
	order.setDateOfTakeBook(Calendar.getInstance());
	order.setDateOfReturnBook(null);
	orderDao.create(order);
    }

    @After
    public void deleteOrder() {
	Order order = null;
	if ((order = orderDao.getById(1L)) != null)
	    orderDao.delete(order);
    }

    @Test
    public void whenGetOrderByIdExpectOrder() {
	assertNotNull(orderDao.getById(2L));
    }

    @Test
    public void whenGetOrderByUserExpectOrder() {
	assertNotNull(orderDao.getByUser(userDao.getById(1L)));
    }

    @Test
    public void whenGetOrderByBookExpectOrder() {
	assertNotNull(orderDao.getByBook(bookDao.getById(1L)));
    }

    @Test
    public void whenGetActualOrderExpectOrder() {
	assertNotNull(orderDao.getAllActualOrders());
    }

}
