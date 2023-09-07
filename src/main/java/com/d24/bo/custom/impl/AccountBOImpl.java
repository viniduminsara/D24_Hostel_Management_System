package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.AccountBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.UserDTO;

public class AccountBOImpl implements AccountBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);

    @Override
    public boolean updateProfileImage(UserDTO userDTO) {
        return userDAO.update(Convertor.toUser(userDTO));
    }

    @Override
    public boolean updateDetails(UserDTO userDTO) {
        return userDAO.update(Convertor.toUser(userDTO));
    }

    @Override
    public UserDTO getUser(String userId) {
        return Convertor.toUserDTO(userDAO.get(userId));
    }
}
