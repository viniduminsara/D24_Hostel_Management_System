package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.UserDTO;

public interface AccountBO extends SuperBO {
    boolean updateProfileImage(UserDTO userDTO);

    boolean updateDetails(UserDTO userDTO);

    UserDTO getUser(String userId);
}
