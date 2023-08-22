package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.UserDTO;

import java.io.IOException;
import java.sql.SQLException;

public interface SignupBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws SQLException, IOException;
}
