package com.ak.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ak.api.dao.IUserDao;
import com.ak.api.services.IActivatorService;
import com.ak.api.services.IRoleService;
import com.ak.api.services.IUserService;
import com.ak.dto.UserDto;
import com.ak.entities.Role;
import com.ak.entities.User;
import com.ak.mappers.UserMapper;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private IActivatorService activatorService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDto> getAllUsersDto() {
	return UserMapper.convertList(userDao.getAll());
    }

    @Override
    public UserDto addUser(UserDto userDto) {
	User user = UserMapper.dtoToEntity(userDto);
	user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	List<Role> roles = new ArrayList<>();
	roles.add(roleService.getRoleById(2));
	user.setRoles(roles);
	user.setEnabled(true);
	userDao.create(user);
	activatorService.addActivator(user, userDto.getEmail());
	return null;
    }

    @Override
    public User getUserById(long id) {
	return userDao.getById(id);
    }

    @Override
    public UserDto getUserDtoById(long id) {
	return UserMapper.entityToDto(getUserById(id));
    }

    @Override
    public String getCurrentUserName() {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	return auth.getName();
    }

    @Override
    public User getCurrentUser() {
	return getUserByUsername(getCurrentUserName());
    }

    @Override
    public UserDto getUserDtoByUsername(String username) {
	return UserMapper.entityToDto(getUserByUsername(username));
    }

    private User getUserByUsername(String username) {
	return userDao.getByUsername(username);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
	return UserMapper.entityToDto(userDao.getByEmail(email));
    }

    @Override
    public void updateUser(UserDto userDto) {
	User user = getCurrentUser();
	if (StringUtils.isNotEmpty(userDto.getUsername())) {
	    user.setUsername(userDto.getUsername());
	}
	if (StringUtils.isNotEmpty(userDto.getEmail())) {
	    user.setEmail(userDto.getEmail());
	}
	if (StringUtils.isNotEmpty(userDto.getPassword())) {
	    user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
	}
	userDao.update(user);
    }

    @Override
    public void setEnabled(long userId, boolean enabled) {
	User user = getUserById(userId);
	user.setEnabled(enabled);
	userDao.update(user);
    }
    
    @Override
    public void delete() {
	userDao.delete(userDao.getById(getCurrentUser().getId()));
    }

}
