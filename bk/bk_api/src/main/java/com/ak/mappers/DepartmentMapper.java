package com.ak.mappers;

import java.util.ArrayList;
import java.util.List;

import com.ak.dto.DepartmentDto;
import com.ak.entities.Department;

public class DepartmentMapper {
	
	private DepartmentMapper() {
	}
	
	public static List<DepartmentDto> convertList(List<Department> entities) {
		List<DepartmentDto> department = new ArrayList<>();
		for (Department entity : entities) {
			DepartmentDto dto = entityToDto(entity);
			department.add(dto);
		}
		return department;
	}

	public static DepartmentDto entityToDto(Department entity) {
		DepartmentDto dto = new DepartmentDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}

	public static Department dtoToEntity(DepartmentDto dto) {
		Department entity = new Department();
		entity.setName(dto.getName());
		return entity;
	}
}
