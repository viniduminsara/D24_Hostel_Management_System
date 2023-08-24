package com.d24.dao;

import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

public class Convertor {

    public static Student toStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setContactNo(studentDTO.getContactNo());
        student.setDob(studentDTO.getDob());
        student.setGender(studentDTO.getGender());
        return student;
    }

    public static StudentDTO toStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setContactNo(student.getContactNo());
        studentDTO.setDob(student.getDob());
        studentDTO.setGender(student.getGender());
        return studentDTO;
    }
}
