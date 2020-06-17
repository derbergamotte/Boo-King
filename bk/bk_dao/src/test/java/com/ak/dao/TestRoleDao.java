package com.ak.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ak.api.dao.IRoleDao;
import com.ak.entities.Role;

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
@SpringBootApplication(scanBasePackages = { "com.ak" })
@EntityScan(basePackages = { "com.ak.entities" })
@EnableJpaRepositories(basePackages = { "com.ak.dao" })
@Transactional
public class TestRoleDao {

    @Autowired
    private IRoleDao roleDao;

    @Before
    public void createRole() {
	Role role = new Role();
	role.setName("RoleName");
	roleDao.create(role);
    }

    @After
    public void deleteRole() {
	Role role = null;
	if ((role = roleDao.getById(1L)) != null) {
	    roleDao.delete(role);
	}
    }

    @Test
    public void whenGetRoleByIdExpectRole() {
	assertNotNull(roleDao.getById(1L));
    }

    @Test
    public void whenUpdateRoleExpectNewName() {
	Role role = roleDao.getById(1L);
	role.setName("NewRoleName");
	roleDao.update(role);
	assertTrue(roleDao.getById(1L).getName().equals("NewRoleName"));
    }

}
