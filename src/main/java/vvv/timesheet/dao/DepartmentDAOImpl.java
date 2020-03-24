package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.Enterprise;
import vvv.timesheet.model.User;

import java.util.List;

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

    private String id;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Department getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Department.class, id);
    }

    @Override
    public List<Department> getDepartmentsByUser(User user) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("from Department" +
//                "                           inner join User where User.department=?")
//                .setParameter(0,user.getId())
//                .list();
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Department where user=:user").setParameter("user", user).list();
    }

    @Override
    public List<Department> getDepartments() {
        Session session = sessionFactory.getCurrentSession();
        List <Department> list = session.createQuery("from Department").list();
        //List <String> list = session.createQuery("select name from Department ").list();
        //List <Enterprise> list1= session.createQuery("from Enterprise ").list();
        return  list;
    }
}
