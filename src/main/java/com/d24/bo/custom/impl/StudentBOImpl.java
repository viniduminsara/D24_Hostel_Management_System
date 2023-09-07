package com.d24.bo.custom.impl;

import com.d24.bo.custom.StudentBO;
import com.d24.bo.Convertor;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO studentDTO){
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.add(student);
    }

    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            StudentDTO studentDTO = Convertor.toStudentDTO(student);
            studentDTOS.add(studentDTO);
        }

        return studentDTOS;
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO){
        Student student = Convertor.toStudent(studentDTO);
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(String studentId){
        return studentDAO.delete(studentId);
    }

    @Override
    public boolean existStudent(String studentId){
        return studentDAO.exists(studentId);
    }
}
