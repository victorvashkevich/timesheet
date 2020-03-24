package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Employee;
import vvv.timesheet.model.HoursWorked;
import vvv.timesheet.model.User;

@Repository
public class HoursWorkedDAOImpl implements HoursWorkedDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HoursWorked getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(HoursWorked.class,id);
    }

    @Override
    public HoursWorked get(Employee employee, String period) {
        Session session = sessionFactory.getCurrentSession();
        HoursWorked hoursWorked = (HoursWorked) session.createQuery("from HoursWorked where employee=:employee and period=:period")
                .setParameter("employee",employee)
                .setParameter("period",period).uniqueResult();
        return  hoursWorked;
    }
}
