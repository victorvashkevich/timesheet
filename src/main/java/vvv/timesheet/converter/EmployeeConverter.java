package vvv.timesheet.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vvv.timesheet.dao.EmployeeDAO;
import vvv.timesheet.model.Employee;
import vvv.timesheet.service.EmployeeService;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Override
    public Employee convert(String id) {

        if (id==null || id.isEmpty()) {
            return null;
        }
        Employee employee = employeeService.getById(id);
         return employee;

    }
}
