package com.ak.api.dao;

import com.ak.entities.User;

public interface IUserDao extends IAGenericDao<User> {

    User getByUsername(String username);

    User getByEmail(String email);
}
