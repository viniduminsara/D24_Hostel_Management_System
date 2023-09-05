package com.d24.util;

import com.d24.entity.Reservation;
import com.d24.entity.Room;
import com.d24.entity.Student;
import com.d24.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration factoryConfiguration;

    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration();
        Properties properties = new Properties();

        try {
            //get properties from file
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/hibernate.properties");
            properties.load(fileInputStream);
        }catch (IOException e){
            e.printStackTrace();
        }

        //add properties to configuration
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class);

        //build session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
