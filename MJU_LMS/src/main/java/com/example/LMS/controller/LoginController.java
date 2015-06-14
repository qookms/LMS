package com.example.LMS.controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.LMS.model.LoginInfo;
import com.example.LMS.model.UserInfo;
import com.example.LMS.service.impl.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(CourseController.class);
	
	@RequestMapping(value = "/LoginController/login.do", method = RequestMethod.POST)
	public String login(HttpSession session, HttpServletRequest request)
			throws UnsupportedEncodingException{

		request.setCharacterEncoding("utf-8");
		String userID = request.getParameter("ID");
		String userPW = request.getParameter("Password");
		
		LoginInfo logininfo = new LoginInfo(userID, userPW);
		
		logger.info("로그인 시도 ID:" + userID + ", PW:" + userPW);
		
		UserInfo sessionUser = loginService.login(logininfo);
		session.setAttribute("User", sessionUser);
		
		return "login";
	}
	
	@RequestMapping(value = "/LoginController/joinPage.do", method = RequestMethod.GET)
	public String joinPage(HttpSession session) {
		return "join";
	}
	
	@RequestMapping(value = "/LoginController/join.do", method = RequestMethod.POST)
	public String join(HttpSession session, HttpServletRequest request) {
		logger.info("회원가입 신청");
		
		String userID = request.getParameter("ID");
		String userName = request.getParameter("Name");
		String userPW = request.getParameter("Password");
		String Category = request.getParameter("Category");
		
		loginService.registForLMS(userID, userName, userPW, Category);
		return "home";
		
	}
	
	@RequestMapping(value = "/LoginController/logout.do")
	public String logout(HttpSession session) {
		logger.info("로그아웃");
		if (session.getAttribute("sessionUser") != null) {
			session.removeAttribute("sessionUser");
		}
		return "home";
	}
}
