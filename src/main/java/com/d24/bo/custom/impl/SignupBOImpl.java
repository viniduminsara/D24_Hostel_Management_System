package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.SignupBO;
import com.d24.dao.custom.UserDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.UserDTO;

public class SignupBOImpl implements SignupBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO){
        return userDAO.add(Convertor.toUser(userDTO));
    }
}
