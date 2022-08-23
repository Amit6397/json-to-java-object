package com.sunglowsys.test;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunglowsys.domain.Customer;
import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class TestJackson {
    public static void main(String[] args) throws Exception {

        //read json file
       String jsonData = FileUtils.readFileToString(new File("E:/json/customer.json"), StandardCharsets.UTF_8);
        System.out.println(jsonData);

        //convert to java object


        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.readValue(jsonData,Customer.class);
        System.out.println(customer);

        //save data database

        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

}
