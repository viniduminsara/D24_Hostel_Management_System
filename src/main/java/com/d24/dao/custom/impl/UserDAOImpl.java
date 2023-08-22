package com.d24.dao.custom.impl;

import com.d24.dao.custom.UserDAO;
import com.d24.entity.User;
import com.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User entity) throws SQLException, IOException {
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
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public boolean exists(String s) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        return null;
    }
}
