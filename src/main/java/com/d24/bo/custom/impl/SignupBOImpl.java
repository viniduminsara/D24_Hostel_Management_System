package com.d24.bo.custom.impl;

import com.d24.bo.custom.SignupBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.custom.impl.UserDAOImpl;
import com.d24.dto.UserDTO;
import com.d24.entity.User;

import java.io.IOException;
import java.sql.SQLException;

public class SignupBOImpl implements SignupBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, IOException {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        return userDAO.add(user);
    }
}
