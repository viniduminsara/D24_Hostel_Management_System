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

public class RoomTM {

    private String roomTypeId;
    private String type;
    private Double keyMoney;
    private Integer qty;
    private HBox hBox;

}
