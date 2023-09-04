package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.RoomDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface RoomBO extends SuperBO {
    List<RoomDTO> getAllRooms() throws SQLException, IOException;

    boolean saveRoom(RoomDTO roomDTO) throws SQLException, IOException;

    boolean updateRoom(RoomDTO roomDTO) throws SQLException, IOException;

    boolean deleteStudent(String roomTypeId) throws SQLException, IOException;

    boolean existRoom(String text) throws SQLException, IOException;
}
