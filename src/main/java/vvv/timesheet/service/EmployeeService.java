package vvv.timesheet.service;

import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeesByDepartment(Department department);
    Employee getById(String id);
}
