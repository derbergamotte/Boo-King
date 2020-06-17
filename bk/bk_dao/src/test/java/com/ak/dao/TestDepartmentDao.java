package com.ak.dao;

import static org.junit.Assert.assertNotNull;

import com.ak.api.dao.IDepartmentDao;
import com.ak.entities.Department;

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
public class TestDepartmentDao {

    @Autowired
    private IDepartmentDao departmentDao;

    @Before
    public void createDepartment() {
	Department department = new Department();
	department.setName("Department");
	departmentDao.create(department);
    }
    
    @After
    public void deleteDepartment() {
	Department department = null;
	if ((department = departmentDao.getById(1L)) != null) {
	    departmentDao.delete(department);
	}
    }

    @Test
    public void whenGetDepartmentByIdExpectDepartment() {
	assertNotNull(departmentDao.getById(1L));
    }
    
    @Test
    public void whenGetDepartmentByNameExpectDepartment() {
	assertNotNull(departmentDao.getByDepartmentName("Department"));
    }
    
}
