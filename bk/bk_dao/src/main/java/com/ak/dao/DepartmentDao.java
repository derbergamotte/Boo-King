package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IDepartmentDao;
import com.ak.entities.Department;
import com.ak.entities.Department_;

@Repository
public class DepartmentDao extends AGenericDao<Department> implements IDepartmentDao {

    public DepartmentDao() {
	super(Department.class);
    }

    @Override
    public Department getByDepartmentName(String departmentName) {
	return singleResultByQuery(departmentName, Department_.NAME);
    }

}
