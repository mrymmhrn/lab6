/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.labhibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author dell
 */



public class GeoDatabase {
    private static SessionFactory factory;
    public static void main(String arg[])
    {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        System.out.println("Test");
        GeoDatabase d = new GeoDatabase();

      /* Add few employee records in database */
        String csvFile = "C:\\Users\\dell\\Documents\\NetBeansProjects\\LabHibernate\\src\\GeoLiteCity-Location.csv";
        String line = "";
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] location = line.split(csvSplitBy);
                System.out.println(location.length);
                d.addLocation(location[0], location[1],location[3],location[5], location[6]);



            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Integer addLocation(String locId, String latitude, String longitude, String country, String city) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer LocationID = null;
        try {
            tx = session.beginTransaction();
            GeoLocation loc = new GeoLocation(locId, country,city,latitude,longitude);
            LocationID = (Integer) session.save(loc);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return LocationID;
    }
}