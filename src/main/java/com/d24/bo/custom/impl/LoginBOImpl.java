package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.LoginBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.UserDTO;
import com.d24.entity.User;

public class LoginBOImpl implements LoginBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);

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
