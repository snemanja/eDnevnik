package com.project.elektronski_dnevnik.dtos;

import com.project.elektronski_dnevnik.enums.ROLE;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserDto {

	@NotNull
	private String userName;
	@NotNull
	private String password;

	@NotNull
	private String name;
	@NotNull
	private String surName;

	private String email;

	@NotNull
	private ROLE role;

}
