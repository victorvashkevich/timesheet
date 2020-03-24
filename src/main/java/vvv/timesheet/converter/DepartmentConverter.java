package vvv.timesheet.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;
import vvv.timesheet.service.DepartmentService;

@Component
public class DepartmentConverter implements Converter<String, Department> {

    private DepartmentService departmentService;

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public Department convert(String id) {
        if (id==null || id.isEmpty()) {
            return null;
        }
        Department department = departmentService.getById(id);
        return department;
    }
}
