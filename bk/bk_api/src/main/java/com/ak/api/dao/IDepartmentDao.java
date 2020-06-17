package com.ak.api.dao;

import com.ak.entities.Department;

public interface IDepartmentDao extends IAGenericDao<Department>{
	
	Department getByDepartmentName (String departmentName);
}
