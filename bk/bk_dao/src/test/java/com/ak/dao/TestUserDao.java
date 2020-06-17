package com.ak.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ak.api.dao.IUserDao;
import com.ak.entities.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootApplication(scanBasePackages = {"com.ak"})
@EntityScan(basePackages = { "com.ak.entities" })
@EnableJpaRepositories(basePackages = {"com.ak.dao"})
@Transactional
public class TestUserDao {

    @Autowired
    private IUserDao userDao;

    @Before
    public void createUser() {
	User user = new User();
	user.setUsername("Username");
	user.setEmail("Email");
	user.setPassword("Password");
	user.setEnabled(true);
	userDao.create(user);
    }
    
    @After
    public void deleteUser() {
	User user = null;
	if((user = userDao.getById(1L)) != null)
	userDao.delete(user);
    }

    @Test
    public void whenGetUserByIdExpectUser() {
	assertNotNull(userDao.getById(1L));
    }
    
    @Test
    public void whenGetUserByUsernameExpectUser() {
	assertNotNull(userDao.getByUsername("Username"));
    }
    
    @Test
    public void whenGetUserByEmailExpectUser() {
	assertNotNull(userDao.getByEmail("Email"));
    }

    @Test
    public void whenUpdateUserExpectNewName() {
	User user = userDao.getById(1L);
	user.setUsername("NewUserName");
	userDao.update(user);
	assertTrue(userDao.getById(1L).getUsername().equals("NewUserName"));
    }

}
