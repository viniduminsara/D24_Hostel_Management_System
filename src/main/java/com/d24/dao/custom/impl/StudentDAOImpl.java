package com.d24.dao.custom.impl;

import com.d24.dao.custom.StudentDAO;
import com.d24.entity.Student;
import com.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.persist(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(String s) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student entity) throws SQLException {
        return false;
    }

    @Override
    public boolean exists(String s) throws SQLException {
        return false;
    }

    @Override
    public List<Student> getAll() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("from Student");
            return (List<Student>) query.list();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
