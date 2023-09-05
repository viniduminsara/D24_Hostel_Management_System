package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.RoomDTO;

import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRooms();

    boolean saveRoom(RoomDTO roomDTO);

    boolean updateRoom(RoomDTO roomDTO);

    boolean deleteStudent(String roomTypeId);

    boolean existRoom(String text);
}
