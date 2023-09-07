package com.d24.dao.custom.impl;

import com.d24.dao.custom.UserDAO;
import com.d24.entity.User;
import com.d24.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(User entity){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);
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
    public boolean delete(String s) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User authenticate(String username, String password) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            Query query = session.createQuery("select u from user u where username = ?1 and password = ?2");
            query.setParameter(1,username);
            query.setParameter(2,password);
            return (User) query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return null;
    }
}
