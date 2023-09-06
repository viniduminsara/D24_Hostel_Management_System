package com.d24.bo;

import com.d24.dto.ReservationDTO;
import com.d24.dto.RoomDTO;
import com.d24.dto.StudentDTO;
import com.d24.dto.UserDTO;
import com.d24.entity.Reservation;
import com.d24.entity.Room;
import com.d24.entity.Student;
import com.d24.entity.User;

public class Convertor {

    public static Student toStudent(StudentDTO studentDTO){
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setName(studentDTO.getName());
        student.setAddress(studentDTO.getAddress());
        student.setContactNo(studentDTO.getContactNo());
        student.setDob(studentDTO.getDob());
        student.setGender(studentDTO.getGender());
        return student;
    }

    public static StudentDTO toStudentDTO(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setAddress(student.getAddress());
        studentDTO.setContactNo(student.getContactNo());
        studentDTO.setDob(student.getDob());
        studentDTO.setGender(student.getGender());
        return studentDTO;
    }

    public static RoomDTO toRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomTypeId(room.getRoomTypeId());
        roomDTO.setType(room.getType());
        roomDTO.setKeyMoney(room.getKeyMoney());
        roomDTO.setQty(room.getQty());
        return roomDTO;
    }

    public static Room toRoom(RoomDTO roomDTO) {
        Room room = new Room();
        room.setRoomTypeId(roomDTO.getRoomTypeId());
        room.setType(roomDTO.getType());
        room.setKeyMoney(roomDTO.getKeyMoney());
        room.setQty(roomDTO.getQty());
        return room;
    }

    public static ReservationDTO toReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReservationId(reservation.getReservationId());
        reservationDTO.setDate(reservation.getDate());
        reservationDTO.setStudent(toStudentDTO(reservation.getStudent()));
        reservationDTO.setRoom(toRoomDTO(reservation.getRoom()));
        reservationDTO.setStatus(reservation.getStatus());
        return reservationDTO;
    }

    public static Reservation toReservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(reservationDTO.getReservationId());
        reservation.setDate(reservationDTO.getDate());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setStudent(toStudent(reservationDTO.getStudent()));
        reservation.setRoom(toRoom(reservationDTO.getRoom()));
        return reservation;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setFullName(user.getFullName());
        userDTO.setProfileImage(user.getProfileImage());
        return userDTO;
    }
}
