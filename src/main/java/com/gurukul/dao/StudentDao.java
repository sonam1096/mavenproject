package com.gurukul.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gurukul.model.Student;

public class StudentDao {

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    DataSource ds = new DataSource();

    // Display all students
    public List<Student> getStudentDetails() throws SQLException {
	String query = "select id,name,email,course,age from students";
	conn = ds.getConnection();
	List<Student> studentList = new ArrayList<Student>();
	Statement stmt;
	try {
	    stmt = conn.createStatement();
	    ResultSet rs = stmt.executeQuery(query);
	    if (rs != null) {
		while (rs.next()) {
		    Student obj = new Student();
		    obj.setId(rs.getInt("id"));
		    obj.setName(rs.getString("name"));
		    obj.setEmail(rs.getString("email"));
		    obj.setCourse(rs.getString("course"));
		    obj.setAge(rs.getInt("age"));
		    studentList.add(obj);
		}
	    }
	    return studentList;
	} catch (SQLException e) {
	    System.out.println("SQLException in StudentDAO: " + e.getMessage());
	    return null;
	} finally {
	    conn.close();
	}
    }

    // Display one student

    public Student getOneStudent(int id) throws Exception {
	String query = "select id,name, email, course, age from students where id = ?";
	conn = ds.getConnection();
	try {
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setInt(1, id);
	    ResultSet rs = pstmt.executeQuery();
	    Student obj = null;
	    if (rs.next()) {
		obj = new Student();

		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setEmail(rs.getString("email"));
		obj.setCourse(rs.getString("course"));
		obj.setAge(rs.getInt("age"));
	    } else {
		throw new Exception("No record found");
	    }
	    return obj;
	} catch (SQLException e) {
	    System.out.println("SQLException in LoginDAO: " + e.getMessage());
	    return null;
	} finally {
	    conn.close();
	}
    }

    // insert student

    public boolean insertStudent(String name, String email, String course, int age) throws Exception {
	String query = "insert into students (name, email, course, age) values (?,?,?,?)";
	conn = ds.getConnection();
	try {
	    PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
	    pstmt.setString(1, name);
	    pstmt.setString(2, email);
	    pstmt.setString(3, course);
	    pstmt.setInt(4, age);
	    int a = pstmt.executeUpdate();

	    if (a != 0) {
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
		    int loginId = rs.getInt(a);
		    System.out.println("Inserted new Id = " + loginId);
		}
	    } else {
		throw new Exception("No record inserted");
	    }
	    return true;
	} catch (SQLException e) {
	    System.out.println("SQLException in LoginDAO: " + e.getMessage());
	} finally {
	    conn.close();
	}
	return true;

    }

    public boolean deleteStudent(int id) throws Exception {

	String query = " delete from students where id = ?";
	conn = ds.getConnection();
	try {
	    PreparedStatement pstmt = conn.prepareStatement(query);
	    pstmt.setInt(1, id);
	    int a = pstmt.executeUpdate();
	    if (a != 0) {
		return true;
	    } else {
		throw new IdNotFound("No record found");
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return true;
    }
}
