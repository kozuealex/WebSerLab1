package x.serlab.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DAOImpl implements DAO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

    @Override
    public List<Products> printAll() {
        List<Products> list;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        list = em.createQuery("from Products", Products.class).getResultList();
        em.getTransaction().commit();
        return list;
    }

    @Override
    public List<Products> findById(int id) {
        List<Products> list;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        list = em.createQuery("from Products p where p.id = :Id", Products.class)
                .setParameter("Id", id).getResultList();
        em.getTransaction().commit();
        return list;
    }
}
