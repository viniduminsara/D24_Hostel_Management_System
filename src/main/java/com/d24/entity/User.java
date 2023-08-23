package com.d24.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue(generator = "User-Id-Generator")
    @GenericGenerator(name = "User-Id-Generator", strategy = "com.d24.util.id_generator.UserIdGenerator")
    @Column(name = "user_Id")
    private String userId;

    private String fullName;
    private String username;
    private String email;
    private String password;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profileImage;

}
