package vvv.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vvv.timesheet.dao.EmployeeDAO;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> getEmployeesByDepartment(Department department) {
        return employeeDAO.getEmployeesByDepartment(department);
    }

    @Override
    @Transactional
    public Employee getById(String id) {
        return employeeDAO.getById(id);
    }
}
