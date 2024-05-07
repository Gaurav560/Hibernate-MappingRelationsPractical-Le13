package com.telusko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;


public class App {

    public static void main(String[] args) {


        Laptop laptop = new Laptop();
        laptop.setLid(109);
        laptop.setLname("Dell");


        Student student = new Student();
        student.setRollno(6);
        student.setMarks(50);
        student.setName("Navin");
        student.getLaptops().add(laptop);











        //creating configuration class object
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);


        //works without it also without giving deprecation error
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();


        //building session factory which will produce session
        SessionFactory sessionFactory = config.buildSessionFactory(registry);


        System.out.println(sessionFactory);


        //getting session object so that we can perform operations on our object such as save persist etc
        Session session = sessionFactory.openSession();


        //Beginning of transaction
        Transaction tx = session.beginTransaction();


        //save has been deprecated as it creates duplicates ,instead persist has been upgraded
        //u cannot persist outside of transaction begin and commit

        session.persist(laptop);
        session.persist(student);
        tx.commit();


        session.close(); // Close the session
    }
}