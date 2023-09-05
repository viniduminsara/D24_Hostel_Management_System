package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.RoomBO;
import com.d24.dao.custom.RoomDAO;
import com.d24.dao.custom.impl.RoomDAOImpl;
import com.d24.dto.RoomDTO;
import com.d24.entity.Room;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {

    RoomDAO roomDAO = new RoomDAOImpl();

    @Override
    public List<RoomDTO> getAllRooms(){
        List<Room> roomList = roomDAO.getAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room : roomList) {
            roomDTOS.add(Convertor.toRoomDTO(room));
        }
        return roomDTOS;
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO){
        return roomDAO.add(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO){
        return roomDAO.update(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean deleteStudent(String roomTypeId){
        return roomDAO.delete(roomTypeId);
    }

    @Override
    public boolean existRoom(String roomId){
        return roomDAO.exists(roomId);
    }
}
