package com.ak.mappers;

import com.ak.dto.ActivatorDto;
import com.ak.entities.Activator;

public class ActivatorMapper {

    private ActivatorMapper() {
    }

    public static ActivatorDto entityToDto(Activator entity) {
	ActivatorDto dto = new ActivatorDto();
	dto.setId(entity.getId());
	dto.setCode(entity.getCode());
	return dto;
    }
    
}
