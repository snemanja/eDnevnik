package com.project.elektronski_dnevnik.enums;

public enum ROLE {
	ADMIN("Admin"),
	PARENT("Parent"),
	STUDENT("Student"),
	TEACHER("Teacher");

	public String value;

	ROLE(String value) {
		this.value = value;
	}
}
