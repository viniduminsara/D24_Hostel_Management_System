package com.d24.bo.custom.impl;

import com.d24.bo.Convertor;
import com.d24.bo.custom.HomeBO;
import com.d24.dao.custom.QueryDAO;
import com.d24.dao.custom.ReservationDAO;
import com.d24.dao.custom.RoomDAO;
import com.d24.dao.custom.StudentDAO;
import com.d24.dao.factory.DAOFactory;
import com.d24.dao.factory.DAOTypes;
import com.d24.dto.StudentDTO;
import com.d24.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class HomeBOImpl implements HomeBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.STUDENT);
    RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.ROOM);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.RESERVATION);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOTypes.QUERY);

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
