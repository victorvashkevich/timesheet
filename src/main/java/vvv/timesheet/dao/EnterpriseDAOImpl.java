package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Enterprise;

import java.util.List;


@Repository
public class EnterpriseDAOImpl implements EnterpriseDAO{

    private static String code;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Enterprise> allEnterprises() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Enterprise").list();
    }

    @Override
    public void add(Enterprise enterprise) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(enterprise);
    }

    @Override
    public void delete(Enterprise enterprise) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(enterprise);
    }

    @Override
    public void edit(Enterprise enterprise) {
        Session session = sessionFactory.getCurrentSession();
        session.update(enterprise);
    }

    @Override
    public Enterprise getByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Enterprise.class, code);
    }
}
