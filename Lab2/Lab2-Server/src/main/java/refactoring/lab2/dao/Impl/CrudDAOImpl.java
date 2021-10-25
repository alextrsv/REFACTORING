package refactoring.lab2.dao.Impl;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import refactoring.lab2.utils.SessionFactoryBuilder;
import refactoring.lab2.dao.CrudDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class CrudDAOImpl<T> implements CrudDAO<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> tClass;

    public CrudDAOImpl(Class<T> tClass) {
        this.sessionFactory = SessionFactoryBuilder.getSessionFactory();
        this.tClass = tClass;

    }

    @Override
    public List<T> findByCriteria(CriteriaQuery<T> criteriaQuery) {
        EntityManager em = sessionFactory.createEntityManager();
        return em.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Optional<T> findById(Integer id) {
        Session session = openSession();
        T entry = session.get(tClass, id);
        session.close();
        return Optional.ofNullable(entry);
    }

    @Override
    public Optional<T> findByContent(String content) {

        Session session = openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<T> query = cb.createQuery(tClass);
        Root<T> root = query.from(tClass);
        query.select(root);
        query.where(cb.equal(root.get("content"), content));
        try {
            return Optional.ofNullable(session.createQuery(query).getSingleResult());
        }catch (NoResultException noResultException){
            return Optional.empty();
        }
    }


    @Override
    public T update(T entry) {
        Session session = openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(entry);
        tx1.commit();
        session.close();
        return entry;
    }

    @Override
    public void save(T entry) {
        Session session = openSession();
        session.getTransaction().begin();
//        session.merge(entry);
        session.saveOrUpdate(entry);
        session.flush();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager em = sessionFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(this.findById(id));
        em.getTransaction().commit();
    }

    @Override
    public EntityManager createEntityManager() {
        return sessionFactory.createEntityManager();
    }

    @Override
    public Session openSession() {
        return sessionFactory.openSession();
    }

}
