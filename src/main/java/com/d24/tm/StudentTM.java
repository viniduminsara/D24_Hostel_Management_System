package com.d24.tm;

import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class StudentTM {

    private String studentId;
    private String name;
    private String address;
    private String contact;
    private String gender;
    private HBox actionBtn;

}
