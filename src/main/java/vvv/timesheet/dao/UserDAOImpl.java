package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.User;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;
    private String id;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class,id);
    }

    @Override
    public User getByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        //User user  = session.createQuery("from User where name=:name").setParameter("name",name).getSingleResult();
        User user = (User) session.createQuery("from User where name=:name").setParameter("name",name).uniqueResult();
       // List<User> users = session.createQuery("from User where name=:name").setParameter("name",name).list();
        //Query query = session.createQuery("from User where name=:name").setParameter("name",name).uniqueResult();
        return user;
    }

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        //List<Department> users = session.createQuery("select u.departments from User as u where u.name=:name").setParameter("name","victor").list();
        User user = getByName("victor");
        List<Department> users = session.createQuery("from Department where user=:user").setParameter("user",user).list();
        return null;
    }
}
