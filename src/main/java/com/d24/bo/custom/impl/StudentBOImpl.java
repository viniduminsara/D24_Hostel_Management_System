package com.d24.bo.custom.impl;

import com.d24.bo.custom.StudentBO;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.custom.impl.StudentDAOImpl;
import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<StudentDTO> getAllStudents() throws SQLException, IOException {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentId(student.getStudentId());
            studentDTO.setName(student.getName());
            studentDTO.setAddress(student.getAddress());
            studentDTO.setContactNo(student.getContactNo());
            studentDTO.setDob(student.getDob());
            studentDTO.setGender(student.getGender());

            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }
}
