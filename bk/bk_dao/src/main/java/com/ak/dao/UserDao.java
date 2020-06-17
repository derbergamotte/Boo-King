package com.ak.dao;

import org.springframework.stereotype.Repository;

import com.ak.api.dao.IUserDao;
import com.ak.entities.User;
import com.ak.entities.User_;

@Repository
public class UserDao extends AGenericDao<User> implements IUserDao {

    public UserDao() {
	super(User.class);
    }

    public User getByUsername(String username) {
	return singleResultByQuery(username, User_.USERNAME);
    }

    public User getByEmail(String email) {
	return singleResultByQuery(email, User_.EMAIL);
    }

}
