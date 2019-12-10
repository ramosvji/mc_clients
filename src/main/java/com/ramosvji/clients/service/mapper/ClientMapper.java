package com.ramosvji.clients.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ramosvji.clients.controller.dto.RoleDto;
import com.ramosvji.clients.entity.Rol;


@Component
public class ClientMapper {
	
	public List<Rol> rolesMapper(List<RoleDto> rolesDto) {
		List<Rol> roles = new ArrayList<>();
		for(RoleDto rolDto: rolesDto) {
			Rol rol = new Rol();
			
			rol.setName(rolDto.getName());
			rol.setDescription(rolDto.getDescription());
			
			roles.add(rol);
		}
		
		return roles;
	}

}
