package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;

import java.util.List;

@Repository
public class EmployeesDAOImpl implements EmployeeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getEmployeesByDepartment(Department department) {

        Session session = sessionFactory.getCurrentSession();
        List<Employee> employees = session.createQuery("from Employee  where department =:department").setParameter("department",department).list();

        return  employees;
    }

    @Override
    public Employee getById(String id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void addEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(employee);
    }
}
