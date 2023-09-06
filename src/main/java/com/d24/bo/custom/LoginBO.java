package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.UserDTO;

public interface LoginBO extends SuperBO {

    boolean authenticateUser(String username, String password);

    UserDTO getUser(String username, String password);
}
