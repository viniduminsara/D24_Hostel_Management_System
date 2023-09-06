package com.d24.dao.custom;

import com.d24.dao.CrudDAO;
import com.d24.entity.Room;

public interface RoomDAO extends CrudDAO<Room,String> {
    Room get(String roomId);

    String getCount();
}
