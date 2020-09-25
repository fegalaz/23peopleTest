package com.peopletest.service;

import java.util.List;

import com.peopletest.controller.dto.CoursesDTO;
import com.peopletest.entity.Courses;

public interface CoursesService {

	CoursesDTO createCourses(CoursesDTO coursesRequests);
	
	Courses findCoursesByCode(String code);

	List<Courses> getAll();
}
