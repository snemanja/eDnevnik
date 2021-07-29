package com.project.elektronski_dnevnik.controllers;

import com.project.elektronski_dnevnik.dtos.UserDto;
import com.project.elektronski_dnevnik.entities.*;
import com.project.elektronski_dnevnik.entities.persons.Parent;
import com.project.elektronski_dnevnik.entities.persons.Person;
import com.project.elektronski_dnevnik.entities.persons.Student;
import com.project.elektronski_dnevnik.entities.persons.Teacher;
import com.project.elektronski_dnevnik.enums.ROLE;
import com.project.elektronski_dnevnik.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@PostMapping
	public User createUser(@RequestBody UserDto userDto) {
		User u = new User();
		u.setPassword(userDto.getPassword());
		u.setUserName(userDto.getUserName());
		switch (userDto.getRole()) {
			case ADMIN:
				u.setRole(ROLE.ADMIN);
				Person person = new Person();
				person.setName(userDto.getName());
				person.setSurName(userDto.getSurName());
				personRepository.save(person);
				u.setPerson(person);
				saveUser(u);
				log.info("Dodat je novi admin: " + person.getName() + " " + person.getSurName());
				break;
			case PARENT:
				u.setRole(ROLE.PARENT);
				Parent parent = new Parent();
				parent.setName(userDto.getName());
				parent.setSurName(userDto.getSurName());
				parent.setEmail(userDto.getEmail());
				personRepository.save(parent);
				u.setPerson(parent);
				saveUser(u);
				log.info("Dodat je novi roditelj: " + parent.getName() + " " + parent.getSurName());
				break;
			case STUDENT:
				u.setRole(ROLE.STUDENT);
				Student student = new Student();
				student.setName(userDto.getName());
				student.setSurName(userDto.getSurName());
				personRepository.save(student);
				u.setPerson(student);
				saveUser(u);
				log.info("Dodat je novi ucenik: " + student.getName() + " " + student.getSurName());
				break;
			case TEACHER:
				u.setRole(ROLE.TEACHER);
				Teacher teacher = new Teacher();
				teacher.setName(userDto.getName());
				teacher.setSurName(userDto.getSurName());
				personRepository.save(teacher);
				u.setPerson(teacher);
				saveUser(u);
				log.info("Dodat je novi nastavnik: " + teacher.getName() + " " + teacher.getSurName());
				break;
		}
		return u;
	}

	@PostMapping("/addStudent")
	public void addStudentToParent(@RequestParam("parentId") int parentId, @RequestParam("studentId") Integer studentId) {
			Parent parent = parentRepository.findById(parentId).get();
			Student student = studentRepository.findById(studentId).get();
			student.setParent(parent);
			studentRepository.save(student);
			log.info(String.format("Učenik %s %s je dete roditelja %s %s", student.getName(), student.getSurName(), parent.getName(), parent.getSurName()));
	}

	@PutMapping("/updateStudent/{studentId}")
	public Student updateStudent(@PathVariable Integer studentId, @RequestBody Student newStudent){
		Student student = studentRepository.findById(studentId).get();
		if(newStudent.getName() != null) {
			student.setName(newStudent.getName());
		}
		if(newStudent.getSurName() != null) {
			student.setSurName(newStudent.getSurName());
		}
		log.info(String.format("Učenik %s %s je izmenjen.", student.getName(), student.getSurName()));
		return studentRepository.save(student);
	}

	@DeleteMapping("/deleteStudent")
	public void deleteStudent(@RequestParam("studentId") List<Integer> studentIds) {
		log.info(String.format("Ucenik je izbrisan."));
		studentRepository.deleteAllById(studentIds);
	}

	@PutMapping("/updateTeacher/{teacherId}")
	public Teacher updateTeacher(@PathVariable Integer teacherId, @RequestBody Teacher newTeacher) {
		Teacher teacher = teacherRepository.findById(teacherId).get();
		if(newTeacher.getName() != null) {
			teacher.setName(newTeacher.getName());
		}
		if(newTeacher.getSurName() != null) {
			teacher.setSurName(newTeacher.getSurName());
		}
		log.info("Profesor je izmenjen.");
		return teacherRepository.save(teacher);
	}

	@DeleteMapping("/deleteTeacher")
	public void deleteTeacher(@RequestParam("teacherId") List<Integer> teacherIds) {
		log.info("Nastavnik je izbrisan.");
		teacherRepository.deleteAllById(teacherIds);
	}

	@PutMapping("/updateParent/{parentId}")
	public Parent updateParent(@PathVariable Integer parentId, @RequestBody Parent newParent) {
		Parent parent = parentRepository.findById(parentId).get();
		if(newParent.getName() != null) {
			parent.setName(newParent.getName());
		}
		if(newParent.getSurName() != null) {
			parent.setSurName(newParent.getSurName());
		}
		log.info("Roditelj je izmenjen.");
		return parentRepository.save(parent);
	}

	@DeleteMapping("/deleteParent")
	public void deleteParent(@RequestParam("parentId") List<Integer> parentIds) {
		log.info("Roditelj je izbrisan.");
		parentRepository.deleteAllById(parentIds);
	}

	private void saveUser(User user) {
		userRepository.save(user);
	}
}
