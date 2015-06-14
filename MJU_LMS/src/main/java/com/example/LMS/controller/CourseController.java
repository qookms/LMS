package com.example.LMS.controller;

import java.util.ArrayList;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.LMS.model.Course;
import com.example.LMS.model.Grade;
import com.example.LMS.model.Lecture;
import com.example.LMS.model.UserInfo;
import com.example.LMS.service.impl.CourseService;

@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);

	//강좌 목록 불러오기
	@RequestMapping(value = "/CourseController/registForLecturePage", method = RequestMethod.GET)
	public ModelAndView showRequestForLecturePage(HttpSession session, HttpServletRequest request) {
		logger.info("강좌목록 : 개설 중인 강좌");
		UserInfo user = (UserInfo) session.getAttribute("User");
		String lecture_professor = user.getId();
		ModelAndView model = new ModelAndView();	
		ArrayList<Lecture> allLectures = courseService.getAllLectures(lecture_professor);
		model.addObject("allLectures", allLectures);
		model.setViewName("registForLecture");
		return model;
	}
	//강좌 개설하기
	@RequestMapping(value = "/CourseController/registForLecture", method = RequestMethod.POST)
	public String requestForLecture(HttpSession session, HttpServletRequest request) {
		UserInfo user = (UserInfo) session.getAttribute("User");
		
		String lecture_id = request.getParameter("lecture_id").toString();
		String lecture_name = request.getParameter("lecture_name").toString();
		String lecture_professor = user.getId();
		String lecture_year = request.getParameter("lecture_year").toString();
		String lecture_level = request.getParameter("lecture_level").toString();
		String lecture_limit = request.getParameter("lecture_limit").toString();
		String lecture_score = request.getParameter("lecture_score").toString();
		
		logger.info("강좌개설:개설 신청한 강좌 번호->" + lecture_id);
		courseService.registForLecture(lecture_id, lecture_name, lecture_professor, lecture_year, lecture_level, lecture_limit, lecture_score);
		
		return "redirect:registForLecturePage";
	}
	//강좌 삭제
	@RequestMapping(value = "/CourseController/deleteLecture", method = RequestMethod.POST)
	public String deleteLecture(HttpSession session, HttpServletRequest request){
		String lecture_id = request.getParameter("lecture_id").toString();
		logger.info("강좌삭제 : 삭제 신청한 강좌 번호->" + lecture_id);
		courseService.deleteLecture(lecture_id);
		return "redirect:registForLecturePage";
	}
	//성적 부여 화면 - 개설 중인 강좌
	@RequestMapping(value = "/CourseController/gradeManagerPage", method = RequestMethod.GET)
	public ModelAndView showRequestForGradeManagerPage(HttpSession session, HttpServletRequest request){
		logger.info("성적부여 : 개설 중인 강좌");
		UserInfo user = (UserInfo) session.getAttribute("User");
		String lecture_professor = user.getId();
		ModelAndView model = new ModelAndView();	
		ArrayList<Lecture> allLectures = courseService.getAllLectures(lecture_professor);
		model.addObject("allLectures", allLectures);
		model.setViewName("gradeManager");
		return model;
	}
	//성적 부여 화면 - 해당 강좌를 수강 중인 학생
	@RequestMapping(value = "/CourseController/gradeEditPage", method = RequestMethod.POST)
	public ModelAndView showRequestForGradeEditPage(HttpSession session, HttpServletRequest request){
		logger.info("성적부여 : 강좌를 수강 중인 학생");
		String lecture_id = request.getParameter("lecture_id").toString();
		
		ModelAndView model = new ModelAndView();	
		ArrayList<Grade> allGrade = courseService.getAllGrade(lecture_id);
		model.addObject("allGrades", allGrade);
		model.setViewName("gradeEdit");
		return model;
	}
	//성적 부여 화면 - 성적 부여
	@RequestMapping(value = "/CourseController/gradeAssign", method = RequestMethod.POST)
	public String GradeAssign(HttpSession session, HttpServletRequest request){
		String student_id = request.getParameter("student_id").toString();
		String lecture_id = request.getParameter("lecture_id").toString();
		String Grade = request.getParameter("Grade").toString();
		logger.info("성적부여 : 성적을 부여한 학생 -> " + student_id);
		courseService.gradeAssign(student_id, lecture_id, Grade);
		return "redirect:gradeManagerPage";
	}
	//수강신청 목록 불러오기
	@RequestMapping(value = "/CourseController/registForCoursePage", method = RequestMethod.GET)
	public ModelAndView showRequestForCoursePage() {
		logger.info("수강신청 : 수강 과목 나열");
		ModelAndView model = new ModelAndView();
		ArrayList<Course> allCourses = courseService.getAllCourses();
		model.addObject("allCourses", allCourses);
		model.setViewName("registForCourse");
		return model;
	}
	//수강신청
	@RequestMapping(value = "/CourseController/registForCourse", method = RequestMethod.POST)
	public String requestForCourse(HttpSession session, HttpServletRequest request) {
		UserInfo user = (UserInfo) session.getAttribute("User");
		String id = user.getId();
		String lecture_id = request.getParameter("lecture_id");
		
		logger.info("수강신청 : 수강 신청한 과목 번호->" + lecture_id);
		courseService.registForCourse(id, lecture_id);
		return "redirect:registForCoursePage";
	}
	//책가방 - 수강 중인 강좌
	@RequestMapping(value = "/CourseController/checkCourse", method = RequestMethod.GET)
	public ModelAndView checkCourse(HttpSession session, HttpServletRequest request){
		UserInfo user = (UserInfo) session.getAttribute("User");
		String id = user.getId();
		
		logger.info("책가방 : 수강내역");
		ModelAndView model = new ModelAndView();
		ArrayList<Course> allCourses = courseService.checkCourse(id);
		model.addObject("allCourses", allCourses);
		model.setViewName("checkCourse");
		return model;
	}
	//책가방 - 수강 취소
	@RequestMapping(value = "/CourseController/deleteCourse", method = RequestMethod.POST)
	public String deleteCourse(HttpSession session, HttpServletRequest request){
		UserInfo user = (UserInfo) session.getAttribute("User");
		String id = user.getId();
		String lecture_id = request.getParameter("lecture_id");
		
		logger.info("수강삭제 : 삭제 신청한 과목 번호->" + lecture_id);
		courseService.deleteCourse(id, lecture_id);
		return "redirect:checkCourse";
	}
	//성적열람
	@RequestMapping(value = "/CourseController/checkGradePage", method = RequestMethod.GET)
	public ModelAndView showRequestForcheckGradePage(HttpSession session, HttpServletRequest request) {
		UserInfo user = (UserInfo) session.getAttribute("User");
		String id = user.getId();
		logger.info("성적열람 : 수강 과목 나열");
		ModelAndView model = new ModelAndView();
		ArrayList<Grade> allGrades = courseService.getPrivateGrade(id);
		model.addObject("allGrades", allGrades);
		model.setViewName("checkGrade");
		return model;
	}
}