package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.ReservationBO;
import com.d24.dao.custom.ReservationDAO;
import com.d24.dao.custom.RoomDAO;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.ReservationDTO;
import com.d24.dto.RoomDTO;
import com.d24.dto.StudentDTO;
import com.d24.entity.Reservation;
import com.d24.entity.Room;
import com.d24.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);

    @Override
    public List<ReservationDTO> getAllReservations(){
        List<Reservation> reservationList = reservationDAO.getAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation : reservationList) {
            reservationDTOS.add(Convertor.toReservationDTO(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public List<StudentDTO> getAllStudents(){
        List<Student> studentList = studentDAO.getAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : studentList) {
            studentDTOS.add(Convertor.toStudentDTO(student));
        }
        return studentDTOS;
    }

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
    public StudentDTO getStudent(String studentId){
        return Convertor.toStudentDTO(studentDAO.get(studentId));
    }

    @Override
    public RoomDTO getRoom(String roomId) {
        return Convertor.toRoomDTO(roomDAO.get(roomId));
    }

    @Override
    public boolean saveReservation(ReservationDTO reservationDTO) {
        return reservationDAO.add(Convertor.toReservation(reservationDTO));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) {
        return roomDAO.update(Convertor.toRoom(roomDTO));
    }

    @Override
    public boolean cancelReservation(ReservationDTO reservationDTO) {
        return reservationDAO.update(Convertor.toReservation(reservationDTO));
    }
}
