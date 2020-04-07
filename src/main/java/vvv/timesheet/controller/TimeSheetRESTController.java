package vvv.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.Employee;
import vvv.timesheet.model.HoursWorked;
import vvv.timesheet.model.TimeSheetRow;
import vvv.timesheet.service.*;

import java.util.List;

@Controller
public class TimeSheetRESTController {

    private EnterpriseService enterpriseService;
    private DepartmentService departmentService;
    private UserService userService;
    private TimeSheetService timeSheetService;
    private EmployeeService employeeService;
    private HoursWorkedService hoursWorkedService;

    @Autowired
    public void setEnterpriseService(EnterpriseService  enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @Autowired
    public void setHoursWorkedService(HoursWorkedService hoursWorkedService) {
        this.hoursWorkedService = hoursWorkedService;
    }

    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setTimeSheetService(TimeSheetService timeSheetService) {
        this.timeSheetService = timeSheetService;
    }

    @RequestMapping(value = "/fuck", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Employee> getFuck(@RequestParam("id") String departmentId) {

        Department department = departmentService.getById(departmentId);
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return employees;
    }

    @RequestMapping(value = "/fuck1", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    HoursWorked getFuck1(@RequestParam("employee") Employee employee, @RequestParam("period") String period) {

        HoursWorked hoursWorked = hoursWorkedService.get(employee, period);
        return hoursWorked;

    }

    @RequestMapping(value = "/fuck3", method = RequestMethod.POST)
    public @ResponseBody int postFuck2(@RequestBody TimeSheetRow timeSheetRow) {

        timeSheetService.addTimeSheetRow(timeSheetRow);
        return timeSheetRow.getId();

    }
}
