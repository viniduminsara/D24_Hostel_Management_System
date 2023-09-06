package com.d24.bo.custom;

import com.d24.bo.SuperBO;
import com.d24.dto.StudentDTO;

import java.util.List;

public interface HomeBO extends SuperBO {
    String getStudentCount();

    String getRoomCount();

    String getReservationCount();

    List<StudentDTO> getPendingPayments();
}
