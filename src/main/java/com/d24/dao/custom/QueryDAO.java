package com.d24.dao.custom;

import com.d24.dao.SuperDAO;
import com.d24.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Student> getPendingPayments();
}
