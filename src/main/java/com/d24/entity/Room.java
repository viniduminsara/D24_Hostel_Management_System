package com.d24.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public class Room {

    @Id
    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL,targetEntity = Reservation.class)
    private List<Reservation> reservations;

}
