package com.ak.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class DepartmentDto extends ADto{

	private String name;
}