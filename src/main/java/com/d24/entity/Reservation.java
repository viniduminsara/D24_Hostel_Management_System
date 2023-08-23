package com.d24.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Reservation {

    @Id
    private String reservationId;
    private LocalDate date;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Room room;


}
