package vvv.timesheet.service;

import vvv.timesheet.model.Department;
import vvv.timesheet.model.User;

import java.util.List;

public interface DepartmentService {
    Department getById(String id);
    List<Department> getDepartments();
    List<Department> getDepartmentsByUser(User user);
}
