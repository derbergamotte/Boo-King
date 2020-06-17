package com.ak.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RoleDto extends ADto {

    private String name;
	private List<Long> listUserId;
}
