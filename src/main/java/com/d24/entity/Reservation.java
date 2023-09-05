package com.d24.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Reservation {

    @Id
    @GeneratedValue(generator = "Reservation-Id-Generator")
    @GenericGenerator(name = "Reservation-Id-Generator", strategy = "com.d24.util.id_generator.ReservationIdGenerator")
    private String reservationId;
    private LocalDate date;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Room room;


}
