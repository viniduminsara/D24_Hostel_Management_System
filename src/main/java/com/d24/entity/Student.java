package com.d24.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Student {

    private String studentId;
    private String name;
    private String address;
    private String contactNo;
    private LocalDate dob;
    private String gender;

}
