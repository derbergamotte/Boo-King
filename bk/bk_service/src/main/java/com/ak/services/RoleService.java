package com.ak.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.api.dao.IRoleDao;
import com.ak.api.services.IRoleService;
import com.ak.entities.Role;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public Role getRoleById(long roleId) {
	return roleDao.getById(roleId);
    }

}
