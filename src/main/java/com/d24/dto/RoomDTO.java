package com.d24.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class RoomDTO {

    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;

}
