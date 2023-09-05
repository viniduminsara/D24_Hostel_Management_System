package com.d24.tm;

import javafx.scene.layout.HBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReservationTM {

    private String reservationId;
    private LocalDate date;
    private String studentId;
    private String roomTypeId;
    private String status;
    private HBox hBox;

}
