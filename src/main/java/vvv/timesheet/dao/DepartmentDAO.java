package vvv.timesheet.dao;

import vvv.timesheet.model.Department;
import vvv.timesheet.model.User;

import java.util.List;

public interface DepartmentDAO {

    Department getById(String id);
    List<Department> getDepartmentsByUser(User user);
    List<Department> getDepartments();
}
