package com.d24.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User {

    private String userId;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private byte[] profileImage;

}
