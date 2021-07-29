package com.project.elektronski_dnevnik.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeViewDto {

	private String teacherName;
	private String subjectName;
	private String className;
	private String semesterName;
	private String studentName;
	private int grade;
}
