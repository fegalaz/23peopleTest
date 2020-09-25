package com.peopletest.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.peopletest.controller.dto.CoursesDTO;
import com.peopletest.entity.Courses;
import com.peopletest.service.CoursesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/23people")
@RequiredArgsConstructor
public class ControllerCourses {

	private final static Logger LOGGER = Logger.getLogger(Controller.class.getName());

	@Autowired
	private CoursesService coursesService;

	@PostMapping(value = "/createCourses")
	@ResponseBody
	public ResponseEntity<?> createCourses(@RequestBody CoursesDTO coursesRequests) {

		LOGGER.info("{coursesService.createCourses}");
		CoursesDTO respond = coursesService.createCourses(coursesRequests);
		return new ResponseEntity<CoursesDTO>(respond, HttpStatus.OK);
	}

	@GetMapping(value = "/hello")
	public String firstPage() {
		return "Hello World";
	}
	
	/**
	 * Get Course By Id : It returns the course identified by the given id It returns the course identified by the given id
	 * @param code
	 * @return
	 */
	@GetMapping(value ="/getCourseById")
	public ResponseEntity<Courses> getCourseById(@RequestParam String code){
		
		Courses respond = coursesService.findCoursesByCode(code);
		return new ResponseEntity<Courses>(respond, HttpStatus.OK);
	}
	
	/**
	 * Get All Courses : It returns a paginated list of existing courses
	 * @param code
	 * @return
	 */
	@GetMapping(value ="/getAll")
	public ResponseEntity<List<Courses>> getCourse(){
		
		List<Courses> respond = coursesService.getAll();
		return new ResponseEntity<List<Courses>>(respond, HttpStatus.OK);
	}
	
//	@DeleteMapping(value ="/deleteById")
//	public ResponseEntity<Courses> deleteById(@RequestParam String id){
//		
//		Courses respond = coursesService.deleteCourseById(id);
//		return new ResponseEntity<Courses>(respond, HttpStatus.OK);
//	}

}
