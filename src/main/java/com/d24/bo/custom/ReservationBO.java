package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.ReservationDTO;
import com.d24.dto.RoomDTO;
import com.d24.dto.StudentDTO;

import java.util.List;

public interface ReservationBO extends SuperBO {
    List<ReservationDTO> getAllReservations();

    List<StudentDTO> getAllStudents();

    List<RoomDTO> getAllRooms();

    StudentDTO getStudent(String studentId);

    RoomDTO getRoom(String roomId);

    boolean saveReservation(ReservationDTO reservationDTO);
}
