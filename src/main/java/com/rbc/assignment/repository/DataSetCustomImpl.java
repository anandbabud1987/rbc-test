package com.rbc.assignment.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Component
public class DataSetCustomImpl implements DataSetCustom{
    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List findByStock(String stock) {
        Query query = em.createQuery("select a from DataSet a where stock=:stock");
        query.setParameter("stock",stock);
        return query.getResultList();
    }
}
