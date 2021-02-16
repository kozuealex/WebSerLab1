package x.serlab.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOMain {
    public static void main(String[] args) throws IOException {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Products products = em.find(Products.class, 1);
//        System.out.println("Name: " + products.getName());
//        em.getTransaction().commit();

//        DAO dao = new DAOImpl();
//        System.out.println(dao.printAll());

        String testJson = "{\"id\":4,\"name\":\"Juice\",\"price\":20}";

        ObjectMapper mapper = new ObjectMapper();
        Products product = mapper.readValue(testJson, Products.class);
        System.out.println(testJson);
        DAO dao = new DAOImpl();
        dao.createNew(product);

    }
}
