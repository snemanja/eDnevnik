package com.project.elektronski_dnevnik.controllers;

import com.project.elektronski_dnevnik.dtos.GradeViewDto;
import com.project.elektronski_dnevnik.entities.school_activities.Grade;
import com.project.elektronski_dnevnik.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	@Autowired
	private GradeRepository gradeRepository;

	@GetMapping("/{studentId}")
	public List<GradeViewDto> getStudentGrades(@PathVariable("studentId") Integer studentId, @RequestParam(value = "mark", required = false) Integer mark) {
		List<Grade> grades;
		if(mark == null) {
			grades = gradeRepository.findAllByStudentId(studentId);
		} else {
			grades = gradeRepository.findAllByStudentIdAndMark(studentId, mark);
		}

		List<GradeViewDto> gradeViewDtos = new ArrayList<>();
		for (Grade grade : grades) {
			GradeViewDto gradeViewDto = new GradeViewDto();
			gradeViewDto.setTeacherName(grade.getSemester().getStudentClass().getTeacherSubject().getTeacher().getName());
			gradeViewDto.setSubjectName(grade.getSemester().getStudentClass().getTeacherSubject().getSubjectName());
			gradeViewDto.setClassName(grade.getSemester().getStudentClass().getName());
			gradeViewDto.setSemesterName(grade.getSemester().getName());
			gradeViewDto.setStudentName(grade.getStudent().getName());
			gradeViewDto.setGrade(grade.getMark());
			gradeViewDtos.add(gradeViewDto);
		}
		return gradeViewDtos;
	}
}
