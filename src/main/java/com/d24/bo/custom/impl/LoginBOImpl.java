package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.LoginBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.custom.impl.UserDAOImpl;
import com.d24.dto.UserDTO;
import com.d24.entity.User;

public class LoginBOImpl implements LoginBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean authenticateUser(String username, String password) {
        User user = userDAO.authenticate(username, password);
        return user != null;
    }

    @Override
    public UserDTO getUser(String username, String password) {
        return Convertor.toUserDTO(userDAO.authenticate(username,password));
    }
}
