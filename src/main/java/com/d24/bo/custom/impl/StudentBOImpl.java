package com.d24.bo.custom.impl;

import com.d24.bo.custom.StudentBO;
import com.d24.dao.Convertor;
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
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.add(student);
    }

    @Override
    public List<StudentDTO> getAllStudents() throws SQLException, IOException {
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = Convertor.toStudentDTO(student);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws SQLException, IOException {
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(String studentId) throws SQLException, IOException {
        return studentDAO.delete(studentId);
    }
}
