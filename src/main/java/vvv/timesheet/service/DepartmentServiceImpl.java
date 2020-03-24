package vvv.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vvv.timesheet.dao.DepartmentDAO;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentService(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    @Transactional
    public Department getById(String id) {
        return departmentDAO.getById(id);
    }

    @Override
    @Transactional
    public List<Department> getDepartments() {
        return departmentDAO.getDepartments();
    }

    @Override
    @Transactional
    public List<Department> getDepartmentsByUser(User user) {
        return departmentDAO.getDepartmentsByUser(user);
    }
}
