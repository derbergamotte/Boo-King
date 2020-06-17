package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ak.dto.UserDto;
import com.ak.entities.Role;
import com.ak.entities.User;

public class UserMapper {
	
	private UserMapper() {
	}
		
	public static List<UserDto> convertList(List<User> entities) {
		List<UserDto> users = new ArrayList<>();
		for (User entity : entities) {
			UserDto dto = entityToDto(entity);
			users.add(dto);
		}
		return users;
	}

	public static UserDto entityToDto(User entity) {
		UserDto dto = new UserDto();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setEnabled(entity.getEnabled());
		dto.setListRoleId(entity.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
		return dto;
	}

	public static User dtoToEntity(UserDto dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setEnabled(dto.getEnabled());
		return entity;
	}
}
