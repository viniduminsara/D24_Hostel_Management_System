package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.HomeBO;
import com.d24.dao.custom.QueryDAO;
import com.d24.dao.custom.ReservationDAO;
import com.d24.dao.custom.RoomDAO;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.custom.impl.QueryDAOImpl;
import com.d24.dao.custom.impl.ReservationDAOImpl;
import com.d24.dao.custom.impl.RoomDAOImpl;
import com.d24.dao.custom.impl.StudentDAOImpl;
import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class HomeBOImpl implements HomeBO {

    StudentDAO studentDAO = new StudentDAOImpl();
    RoomDAO roomDAO = new RoomDAOImpl();
    ReservationDAO reservationDAO = new ReservationDAOImpl();
    QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public String getStudentCount() {
        return studentDAO.getCount();
    }

    @Override
    public String getRoomCount() {
        return roomDAO.getCount();
    }

    @Override
    public String getReservationCount() {
        return reservationDAO.getCount();
    }

    @Override
    public List<StudentDTO> getPendingPayments() {
        List<Student> students = queryDAO.getPendingPayments();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(Convertor.toStudentDTO(student));
        }
        return studentDTOS;
    }
}
