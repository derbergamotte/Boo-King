package com.ak.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ak.api.dao.IDepartmentDao;
import com.ak.api.services.IDepartmentService;
import com.ak.dto.DepartmentDto;
import com.ak.entities.Department;
import com.ak.mappers.DepartmentMapper;

@Service
@Transactional
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentDao departmentDao;

    @Override
    public List<DepartmentDto> getAllDepartmentsDto() {
	return DepartmentMapper.convertList(departmentDao.getAll());
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) {
	Department department = DepartmentMapper.dtoToEntity(departmentDto);
	return DepartmentMapper.entityToDto(departmentDao.create(department));
    }

    @Override
    public Department getDepartmentById(long departmentId) {
	return departmentDao.getById(departmentId);
    }

    @Override
    public DepartmentDto getDepartmentDtoById(long id) {
	return DepartmentMapper.entityToDto(getDepartmentById(id));
    }

    @Override
    public void updateDepartment(long departmentId, DepartmentDto departmentDto) {
	Department department = departmentDao.getById(departmentId);
	department.setName(departmentDto.getName());
	departmentDao.update(department);
    }

    @Override
    public void delete(long departmentId) {
	departmentDao.delete(departmentDao.getById(departmentId));
    }

}
