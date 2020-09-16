package com.peopletest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peopletest.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, String> {

	Courses findCoursesByCode(String code);
}
