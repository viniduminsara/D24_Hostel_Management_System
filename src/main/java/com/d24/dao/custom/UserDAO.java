package com.d24.dao.custom;

import com.d24.dao.CrudDAO;
import com.d24.entity.User;

public interface UserDAO extends CrudDAO<User,String> {
    User authenticate(String username, String password);

    User get(String userId);
}
