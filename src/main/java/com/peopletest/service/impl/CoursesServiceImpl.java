package com.peopletest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peopletest.controller.dto.CoursesDTO;
import com.peopletest.entity.Courses;
import com.peopletest.repository.CoursesRepository;
import com.peopletest.service.CoursesService;

@Service
public class CoursesServiceImpl implements CoursesService {

	@Autowired
	public CoursesRepository coursesRepository;
	
	@Override
	public CoursesDTO createCourses(CoursesDTO coursesRequests) {
		return null;
	}
	
	@Override
	public Courses findCoursesByCode(String code) {
		Courses respond = coursesRepository.findCoursesByCode(code);
		return respond;
	}
	
//	@Override
//	public void deleteCourseById(String id) {
//		Boolean respond = coursesRepository.deleteById(id);
//		return respond;
//	}
	
	@Override
	public List<Courses> getAll() {
		List<Courses> respond = coursesRepository.findAll();
		return respond;
	}
	
}
