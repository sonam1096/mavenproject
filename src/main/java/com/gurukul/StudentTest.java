package com.gurukul;

import java.util.ArrayList;
import java.util.List;

import com.gurukul.dao.StudentDao;
import com.gurukul.model.Student;

public class StudentTest {

    public static void main(String[] args) {
	// TODO Auto-generated method stub
	StudentDao dao = new StudentDao();
	Student obj = new Student();

	List<Student> studentList = new ArrayList<>();

	try {
//		    obj = dao.getPassword("Shrikant");

	    studentList = dao.getStudentDetails();

//	    for (Student loginObj : studentList) {

//		System.out.println(loginObj.getname() + ", " + loginObj.getPswd());
//	    }

	    int passExists = dao.getOneStudent(2).getId();
	    if (passExists != 0) {
		dao.deleteStudent(passExists);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    System.out.println("Student not existes");
	}
    }

}
