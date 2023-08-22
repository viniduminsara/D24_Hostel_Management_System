package com.d24.util;

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

    private FactoryConfiguration() throws IOException {
        Configuration configuration = new Configuration();

        //get properties from file
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/hibernate.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        //add properties to configuration
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(User.class);

        //build session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() throws IOException {
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
