package com.example.LMS.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import com.example.LMS.model.Course;
import com.example.LMS.model.Grade;
import com.example.LMS.model.Lecture;

@Repository
public class CourseDAO{
	
	//수강신청 목록 불러오기, 신청
	public ArrayList<Course> getAllCourses() {
		ArrayList<Course> allCourses = new ArrayList<Course>();
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select l.lecture_id, l.lecture_name, l.lecture_professor, u.name, l.lecture_year, "
							+ "l.lecture_level, l.lecture_score, l.lecture_limit from lecture l"
							+ " join user u on l.lecture_professor = u.id");

			while (rs.next()) {
				Course course = new Course();
				course.setLecture_id(rs.getInt("lecture_id"));
				course.setLecture_name(rs.getString("lecture_name"));
				course.setLecture_professor_id(rs.getString("lecture_professor"));
				course.setLecture_professor_name(rs.getString("name"));
				course.setLecture_year(rs.getInt("lecture_year"));
				course.setLecture_level(rs.getInt("lecture_level"));
				course.setLecture_score(rs.getInt("lecture_score"));
				course.setLecture_limit(rs.getInt("lecture_limit"));
				allCourses.add(course);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allCourses;
	}
	public void registForCourse(String userID, String lecture_id) {
		/* 수강신청 */
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			stmt = conn.prepareStatement("insert into course(id, lecture_id) values(?, ?)");
			stmt.setString(1, userID);
			stmt.setInt(2, Integer.parseInt(lecture_id));
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}

	
	
	// 책가방 목록 불러오기, 삭제
	public ArrayList<Course> checkCourse(String userID) {
		ArrayList<Course> allCourses = new ArrayList<Course>();

		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select l.lecture_id, l.lecture_name, l.lecture_professor, u.name, l.lecture_year, "
					+ "l.lecture_level, l.lecture_score, l.lecture_limit from (lecture l join user u on l.lecture_professor = u.id)"
					+ "join course c on l.lecture_id = c.lecture_id where c.id = ?");
			stmt.setString(1, userID);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setLecture_id(rs.getInt("lecture_id"));
				course.setLecture_name(rs.getString("lecture_name"));
				course.setLecture_professor_id(rs.getString("lecture_professor"));
				course.setLecture_professor_name(rs.getString("name"));
				course.setLecture_year(rs.getInt("lecture_year"));
				course.setLecture_level(rs.getInt("lecture_level"));
				course.setLecture_score(rs.getInt("lecture_score"));
				course.setLecture_limit(rs.getInt("lecture_limit"));
				allCourses.add(course);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allCourses;
	}

	public void deleteCourse(String userID, String lecture_id) {
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			stmt = conn.prepareStatement("delete from course where id = ? and lecture_id= ?");
			stmt.setString(1, userID);
			stmt.setInt(2, Integer.parseInt(lecture_id));
			result = stmt.executeUpdate();

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}

	}	
	
	// 교수 강좌 불러오기, 개설, 삭제	
	public ArrayList<Lecture> getAllLectures(String lecture_professor) {
		ArrayList<Lecture> allLectures = new ArrayList<Lecture>();
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select * from lecture where lecture_professor = ?");
			stmt.setString(1, lecture_professor);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Lecture lecture = new Lecture();
				lecture.setLecture_id(rs.getInt("lecture_id"));
				lecture.setLecture_name(rs.getString("lecture_name"));
				lecture.setLecture_year(rs.getInt("lecture_year"));
				lecture.setLecture_level(rs.getInt("lecture_level"));
				lecture.setLecture_limit(rs.getInt("lecture_limit"));
				lecture.setLecture_score(rs.getInt("lecture_score"));
				
				allLectures.add(lecture);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		/* 모든 과목 정보 */
		return allLectures;
	}
	public void registForLecture(String lecture_id, String lecture_name, String lecture_professor, String lecture_year, String lecture_level, String lecture_limit, String lecture_score) {
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("insert into lecture(lecture_id, lecture_name, lecture_professor, lecture_year, lecture_level, lecture_limit, lecture_score) values(?, ?, ?, ?, ?, ? ,?)");
			stmt.setInt(1, Integer.parseInt(lecture_id));
			stmt.setString(2, lecture_name);
			stmt.setString(3, lecture_professor);
			stmt.setInt(4, Integer.parseInt(lecture_year));
			stmt.setInt(5, Integer.parseInt(lecture_level));
			stmt.setInt(6, Integer.parseInt(lecture_limit));
			stmt.setInt(7, Integer.parseInt(lecture_score));
			
			result = stmt.executeUpdate();

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
			}
		}
	}
	
	public void deleteLecture(String lecture_id) {
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("delete from lecture where lecture_id= ?");
			stmt.setInt(1, Integer.parseInt(lecture_id));
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
			}
		}
	}
	public ArrayList<Grade> getAllGrade(String lecture_id) {
		ArrayList<Grade> allGrades = new ArrayList<Grade>();
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select * from course where lecture_id = ?");
			stmt.setString(1, lecture_id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setStudent_id(rs.getString("id"));
				grade.setLecture_id(rs.getInt("lecture_id"));
				grade.setGrade(rs.getString("lecture_grade"));
				allGrades.add(grade);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return allGrades;
	}
	public void gradeAssign(String student_id, String lecture_id, String Grade) {
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

			stmt = conn.prepareStatement("update course set lecture_grade = ? where id = ? and lecture_id = ?");
			stmt.setString(1, Grade);
			stmt.setString(2, student_id);
			stmt.setInt(3, Integer.parseInt(lecture_id));
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	}
	public ArrayList<Grade> getPrivateGrade(String student_id) {
		ArrayList<Grade> allGrades = new ArrayList<Grade>();
		
		String dbUrl = "jdbc:mysql://121.167.87.37:3306/60112316lms";
		String dbUser = "root";
		String dbPassword = "qoo5665";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			stmt = conn.prepareStatement("select * from (lecture l join user u on l.lecture_professor = u.id) "
					+ "join course c on l.lecture_id = c.lecture_id where c.id = ?");
			stmt.setString(1, student_id);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Grade grade = new Grade();
				grade.setLecture_id(rs.getInt("l.lecture_id"));
				grade.setLecture_name(rs.getString("l.lecture_name"));
				grade.setLecture_professor(rs.getString("u.name"));
				grade.setLecture_year(rs.getInt("l.lecture_year"));
				grade.setLecture_level(rs.getInt("l.lecture_level"));
				grade.setLecture_score(rs.getInt("l.lecture_score"));
				grade.setLecture_limit(rs.getInt("l.lecture_limit"));
				grade.setGrade(rs.getString("c.lecture_grade"));
				allGrades.add(grade);
			}

		} catch (SQLException e) {
		} catch (ClassNotFoundException e) {

		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
		return allGrades;
	}
}
