package x.serlab.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOMain {
    public static void main(String[] args) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
//
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Products products = em.find(Products.class, 1);
//        System.out.println("Name: " + products.getName());
//        em.getTransaction().commit();

        DAO dao = new DAOImpl();
        System.out.println(dao.printAll());
    }
}
