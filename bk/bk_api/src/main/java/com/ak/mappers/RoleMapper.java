package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ak.dto.RoleDto;
import com.ak.entities.Role;
import com.ak.entities.User;

public class RoleMapper {
	
	private RoleMapper() {
	}
		
	public static List<RoleDto> convertList(List<Role> entities) {
		List<RoleDto> roles = new ArrayList<>();
		for (Role entity : entities) {
			RoleDto dto = entityToDto(entity);
			roles.add(dto);
		}
		return roles;
	}

	public static RoleDto entityToDto(Role entity) {
		RoleDto dto = new RoleDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setListUserId(entity.getUsers().stream().map(User::getId).collect(Collectors.toList()));
		return dto;
	}

	public static Role dtoToEntity(RoleDto dto) {
		Role entity = new Role();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		return entity;
	}
}
