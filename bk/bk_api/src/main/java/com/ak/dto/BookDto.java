package com.ak.dto;

import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BookDto extends ADto {

	private BookdetailDto bookdetail;
	private Set<DepartmentDto> listDepartment;
	private double averageRate;

}
