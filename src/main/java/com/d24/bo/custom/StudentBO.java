package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface StudentBO extends SuperBO {
    boolean saveStudent(StudentDTO studentDTO) throws SQLException, IOException;
}
