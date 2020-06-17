package com.ak.dto;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UserDto extends ADto {

	private String username;
	private String email;
	private String password;
	private Boolean enabled;
	private List<Long> listRoleId;
}
