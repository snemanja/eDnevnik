package com.project.elektronski_dnevnik.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.elektronski_dnevnik.dtos.SemesterDto;
import com.project.elektronski_dnevnik.dtos.StudentClassDto;
import com.project.elektronski_dnevnik.dtos.TeacherSubjectDto;
import com.project.elektronski_dnevnik.entities.persons.Student;
import com.project.elektronski_dnevnik.entities.persons.Teacher;
import com.project.elektronski_dnevnik.entities.school_activities.Grade;
import com.project.elektronski_dnevnik.entities.school_activities.Semester;
import com.project.elektronski_dnevnik.entities.school_activities.StudentClass;
import com.project.elektronski_dnevnik.entities.school_activities.TeacherSubject;
import com.project.elektronski_dnevnik.models.EmailObject;
import com.project.elektronski_dnevnik.repositories.GradeRepository;
import com.project.elektronski_dnevnik.repositories.SemesterRepository;
import com.project.elektronski_dnevnik.repositories.StudentClassRepository;
import com.project.elektronski_dnevnik.repositories.StudentRepository;
import com.project.elektronski_dnevnik.repositories.TeacherRepository;
import com.project.elektronski_dnevnik.repositories.TeachersSubjectRepository;
import com.project.elektronski_dnevnik.services.EmailService;

@RestController
@RequestMapping("/api/v1/activities")
public class SchoolActivitiesController {

	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeachersSubjectRepository teachersSubjectRepository;

	@Autowired
	private SemesterRepository semesterRepository;

	@Autowired
	private StudentClassRepository studentClassRepo;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired GradeRepository gradeRepository;
	
	@Autowired
	private EmailService emailService;


	@GetMapping
	public Teacher getTeacher(@RequestParam("teacherId") Integer teacherId) {
		return teacherRepository.findById(teacherId).get();
	}

	@PostMapping("/teacher/{teacherId}/teacherSubject")
	public TeacherSubject createSubject(@PathVariable("teacherId") Integer teacherId, @RequestBody TeacherSubjectDto teacherSubjectDto) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		TeacherSubject ts = new TeacherSubject();
		ts.setSubjectName(teacherSubjectDto.getSubjectName());
		ts.setWeeklyFond(teacherSubjectDto.getWeeklyFond());
		ts.setTeacher(teacher);
		log.info("Dodat je predmet nastavniku: " + ts.toString());
		return teachersSubjectRepository.save(ts);
	}

	@PutMapping("/teacherSubject/{teacherSubjectId}")
	public TeacherSubject updateSubject(@PathVariable Integer teacherSubjectId, @RequestBody TeacherSubject newTeacherSubject) {
		TeacherSubject subject = teachersSubjectRepository.findById(teacherSubjectId).get();
		if(subject.getSubjectName() != null) {
			subject.setSubjectName(newTeacherSubject.getSubjectName());
		}
		if(subject.getWeeklyFond() != null) {
			subject.setWeeklyFond(newTeacherSubject.getWeeklyFond());
		}
		log.info("Predmet je izmenjen.");
		return teachersSubjectRepository.save(subject);
	}

	@DeleteMapping("/teacherSubject/{teachersSubjectId}")
	public void deleteSubject(@PathVariable("teachersSubjectId") Integer teachersSubjectId) {
		log.info("Predmet je izbrisan.");
		teachersSubjectRepository.deleteById(teachersSubjectId);
	}

	@PostMapping("/teacherSubject/{teacherSubjectId}/studentClass")
	public StudentClass createStudentClass(@PathVariable("teacherSubjectId") Integer teacherSubjectId, @RequestBody StudentClassDto studentClassDto) {
		TeacherSubject teacherSubject = teachersSubjectRepository.findById(teacherSubjectId).get();
		StudentClass studentClass = new StudentClass();
		studentClass.setName(studentClassDto.getName());
		studentClass.setTeacherSubject(teacherSubject);
		log.info("Razred je kreiran.");
		return studentClassRepo.save(studentClass);
	}

	@PutMapping("/studentClass")
	public StudentClass updateStudentClass(@RequestBody StudentClass studentClass) {
		log.info("Naziv razreda je izmenjen.");
		return studentClassRepo.save(studentClass);
	}

	@DeleteMapping("/studentClass/{studentClassId}")
	public void deleteStudentClass(@PathVariable("studentClassId") Integer studentClassId) {
		log.info("Razred je izbrisan.");
		teachersSubjectRepository.deleteById(studentClassId);
	}

	@PostMapping("/studentClass/{studentClassId}/semester")
	public Semester createSemester(@PathVariable("studentClassId") Integer studentClassId, @RequestBody SemesterDto semesterDto) {
		StudentClass studentClass = studentClassRepo.findById(studentClassId).get();
		Semester semester = new Semester();
		semester.setName(semesterDto.getName());
		semester.setStudentClass(studentClass);
		log.info("Polugodište dodato.");
		return semesterRepository.save(semester);
	}

	@PutMapping("/semester")
	public Semester updateSemester(@RequestBody Semester semester) {
		log.info("Polugodište izmenjeno.");
		return semesterRepository.save(semester);
	}

	@DeleteMapping("/semester/{semesterId}")
	public void deleteSemester(@PathVariable("semesterId") Integer studentClassId) {
		log.info("Polugodište izbrisano.");
		teachersSubjectRepository.deleteById(studentClassId);
	}

	@PostMapping("/semester/{semesterId}/grade")
	public Grade createGrade(@PathVariable("semesterId") Integer semesterId, @RequestParam("studentId") Integer studentId,
			@RequestParam("mark") Integer mark,	@RequestParam("teacherId") Integer teacherId) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		Semester semester = semesterRepository.findById(semesterId).get();
		Student student = studentRepository.findById(studentId).get();
		Grade grade = new Grade();
		
		grade.setMark(mark);
		grade.setStudent(student);
		grade.setSemester(semester);
		
		
		EmailObject emailObject = new EmailObject();
		emailObject.setTo(student.getParent().getEmail());
		emailObject.setSubject(String.format("%s %s je dobio/la ocenu %s", student.getSurName(), student.getName(), grade.getMark()));
		emailObject.setText(String.format("Vaše dete %s %s je dobilo ocenu %s kod profesora %s %s.", student.getName(),
				student.getSurName(), grade.getMark(), teacher.getName(), teacher.getSurName()));
		
		emailService.sendSimpleMessage(emailObject);
		log.info("Đak ocenjen, roditelju poslat mejl i čaša vode sa kašičicom šećera.");
		return gradeRepository.save(grade);
	}

	@PutMapping("/grade/{gradeId}")
	public Grade updateGrade(@PathVariable Integer gradeId, @RequestBody Grade newGrade) {
		Grade grade = gradeRepository.findById(gradeId).get();
		if(newGrade.getMark() != null) {
			grade.setMark(newGrade.getMark());
		}
		log.info("Ocena je izmenjena.");
		return gradeRepository.save(grade);
	}

	@DeleteMapping("/grade/{gradeId}")
	public void deleteGrade(@PathVariable("gradeId") Integer gradeId) {
		log.info("Ocena je izbrisana, ali ne zaslužuje da mu se skine KEČINA!!");
		gradeRepository.deleteById(gradeId);
	}
}
