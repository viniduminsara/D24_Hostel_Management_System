package com.d24.bo.custom.impl;

import com.d24.bo.custom.StudentBO;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.custom.impl.StudentDAOImpl;
import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws SQLException, IOException {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setContactNo(studentDTO.getContactNo());
        student.setDob(studentDTO.getDob());
        student.setGender(studentDTO.getGender());

        return studentDAO.add(student);
    }
}
