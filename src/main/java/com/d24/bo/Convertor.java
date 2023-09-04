package com.d24.bo;

import com.d24.dto.RoomDTO;
import com.d24.dto.StudentDTO;
import com.d24.entity.Room;
import com.d24.entity.Student;

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
}
