package com.example.LMS.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.dao.impl.CourseDAO;
import com.example.LMS.model.Course;
import com.example.LMS.model.Grade;
import com.example.LMS.model.Lecture;

@Service
public class CourseService{
	@Autowired
	private CourseDAO dao;
	
	//수강신청 목록 불러오기, 신청
	public ArrayList<Course> getAllCourses() {		
		return dao.getAllCourses();
	}

	public void registForCourse(String userID, String lecture_id) {		
		dao.registForCourse(userID, lecture_id);
	}
	public ArrayList<Grade> getPrivateGrade(String student_id) {
		return dao.getPrivateGrade(student_id);
	}
	
	//책가방 목록 불러오기, 삭제
	public ArrayList<Course> checkCourse(String userID) {		
		return dao.checkCourse(userID);
	}
	public void deleteCourse(String userID, String lecture_id) {
		dao.deleteCourse(userID, lecture_id);		
	}
	
	
	//교수 강좌 불러오기, 개설, 삭제
	public ArrayList<Lecture> getAllLectures(String lecture_professor){
		return dao.getAllLectures(lecture_professor);
	}
	public void registForLecture(String lecture_id, String lecture_name, String lecture_professor, String lecture_year, String lecture_level, String lecture_limit, String lecture_score) {	
		dao.registForLecture(lecture_id, lecture_name, lecture_professor, lecture_year, lecture_level, lecture_limit, lecture_score);
	}
	public void deleteLecture(String lecture_id) {
		dao.deleteLecture(lecture_id);
	}
	public ArrayList<Grade> getAllGrade(String lecture_id) {
		return dao.getAllGrade(lecture_id);
	}
	public void gradeAssign(String student_id, String lecture_id, String Grade) {
		dao.gradeAssign(student_id, lecture_id, Grade);
	}

}