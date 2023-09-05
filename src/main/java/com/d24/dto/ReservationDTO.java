package com.d24.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReservationDTO {

    private String reservationId;
    private LocalDate date;
    private StudentDTO student;
    private RoomDTO room;
    private String status;

}
