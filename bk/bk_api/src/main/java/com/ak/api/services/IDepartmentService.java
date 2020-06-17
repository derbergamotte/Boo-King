package com.ak.api.services;

import java.util.List;

import com.ak.dto.DepartmentDto;
import com.ak.entities.Department;

public interface IDepartmentService {

    public List<DepartmentDto> getAllDepartmentsDto();

    DepartmentDto addDepartment(DepartmentDto departmentDto);

    Department getDepartmentById(long departmentId);
    
    DepartmentDto getDepartmentDtoById(long departmentId);

    void updateDepartment(long departmentId, DepartmentDto departmentDto);

    void delete(long departmentId);

}
