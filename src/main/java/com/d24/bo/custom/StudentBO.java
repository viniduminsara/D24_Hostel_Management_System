package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(String studentId);

    boolean existStudent(String studentId);
}
