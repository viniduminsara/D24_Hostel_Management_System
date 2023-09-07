package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.AccountBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.custom.impl.UserDAOImpl;
import com.d24.dto.UserDTO;

public class AccountBOImpl implements AccountBO {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean updateProfileImage(UserDTO userDTO) {
        return userDAO.update(Convertor.toUser(userDTO));
    }
}
