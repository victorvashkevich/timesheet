package vvv.timesheet.dao;

import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> getEmployeesByDepartment(Department department);
    Employee getById(String id);
    void addEmployee(Employee employee);
}
