package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IRoleDao;
import com.ak.entities.Role;

@Repository
public class RoleDao extends AGenericDao<Role> implements IRoleDao {

    public RoleDao() {
	super(Role.class);
    }

}
