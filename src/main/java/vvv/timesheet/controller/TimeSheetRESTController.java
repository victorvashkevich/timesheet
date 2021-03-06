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

    /**
     * Получает по подразделению всех его сотрудников, возвращает на фронт данные в формате json
     * @param departmentId - подразделение
     * @return список сотрудников
     */
    @RequestMapping(value = "/addRow", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Employee> addRow(@RequestParam("id") String departmentId) {

        Department department = departmentService.getById(departmentId);
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        return employees;
    }

    /**
     * Получает отработанное время сотрудников за период
     * @param employee сотрудник
     * @param period период
     * @return строка с отработанными часами
     */
    @RequestMapping(value = "/getHours", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody HoursWorked getHours(@RequestParam("employee") Employee employee, @RequestParam("period") String period) {

        HoursWorked hoursWorked = hoursWorkedService.get(employee, period);
        return hoursWorked;

    }

    /**
     * Сохраняет добавленную строку в базе
     * @param timeSheetRow строка, переданная с фронта в формате json
     * @return id добавленной строки для дальнейшей обработки на фронте
     */
    @RequestMapping(value = "/saveRow", method = RequestMethod.POST)
    public @ResponseBody int saveRow(@RequestBody TimeSheetRow timeSheetRow) {

        timeSheetService.addTimeSheetRow(timeSheetRow);
        return timeSheetRow.getId();

    }
}
