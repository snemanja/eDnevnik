package com.project.elektronski_dnevnik.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TeacherSubjectDto {

	@NotNull
	private String subjectName;
	@NotNull
	private int weeklyFond;
}
