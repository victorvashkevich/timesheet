package vvv.timesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vvv.timesheet.model.*;
import vvv.timesheet.service.*;

import java.util.List;
import java.util.Map;

@Controller
public class TimeSheetController {

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
     * Стартовая страница, авторизуемся используя Spring Security, получаем пользоватетя из модели и по
     * нему получаем список его подразделений и передаем его в представление
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView startPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();
        LdapUserDetailsImpl ldapUserDetails = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String dn  = ldapUserDetails.getDn();
        int beginIndex = dn.indexOf("cn=")+4;
        int endIndex = dn.indexOf(",");
        String username = dn.substring(beginIndex,endIndex);
        modelAndView.addObject("username", username);
        User user = userService.getByName(principalName);
        List<Department> departments = departmentService.getDepartmentsByUser(user);
        modelAndView.addObject("userDepartments", departments);

        return modelAndView;
    }

    /**
     * Страница табелей выбранного подразделения, подразделение выбираем из переменной пути и по нему
     * получаем из модели список табелей и передаем его в представление
     * @param id_department id подразделения
     * @return
     */
    @RequestMapping(value = "/{id_department}/", method = RequestMethod.GET)
    public ModelAndView timeSheetsPage(@PathVariable("id_department") String id_department) {

        Department department  = departmentService.getById(id_department);
        ModelAndView modelAndView = new ModelAndView();
        List<TimeSheet> timeSheets = timeSheetService.getTimeSheetsByDepartment(department);
        modelAndView.addObject("timeSheets",timeSheets);
        modelAndView.addObject("department",department);
        modelAndView.setViewName("timesheets");
        return modelAndView;
    }

    /**
     * Страница редактирования выбранного табеля, по выбранноиму табелю полкучаем все его строки из модели
     * и передаем его в предаствление
     * @param id Id табеля
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id}/", method = RequestMethod.GET)
    public ModelAndView timeSheetPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        TimeSheet timeSheet = timeSheetService.getById(id);
        List<TimeSheetRow> timeSheetRows = timeSheetService.getTimeSheetRows(timeSheet);
        modelAndView.addObject("timeSheet", timeSheet);
        modelAndView.addObject("timeSheetRows", timeSheetRows);
        //пока меняем представление на другое, попробуем jquery
        //modelAndView.setViewName("timesheet");
        modelAndView.setViewName("timesheetnew");
        return modelAndView;
    }

    /**
     * Страницца добавления табеля для подразделения, подразделение берем из пути
     * @param id_department id подразделения
     * @return
     */
    @RequestMapping(value = "/{id_department}/add",method = RequestMethod.GET)
    public ModelAndView addTimeSheetPage(@PathVariable("id_department") String id_department) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("id_department",id_department);
        modelAndView.setViewName("addTimeSheet");
        return modelAndView;
    }

    /**
     * ДОбавление табеля в модель
     * @param timeSheet
     * @param id_department
     * @return
     */
    @RequestMapping(value = "/{id_department}/add",method = RequestMethod.POST)
    public ModelAndView addTimeSheet(@ModelAttribute("TimeSheet") TimeSheet timeSheet, @PathVariable String id_department) {
        ModelAndView modelAndView = new ModelAndView();
        //timeSheet.setDepartment(departmentService.getById(id_department));
        modelAndView.setViewName("redirect:/{id_department}/");
        timeSheetService.addTimeSheet(timeSheet);
        return modelAndView;
    }

    /**
     * Страница для редактирования строки табеля (не используется, будем редактировать строку на фронте)
     * @param id_department id подразделения
     * @param id_timesheet id табеля
     * @param id_row id строки табеля
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/{id_row}",method = RequestMethod.GET)
    public ModelAndView editTimeSheetRowPage(@PathVariable String id_department, @PathVariable int id_timesheet, @PathVariable int id_row) {
        ModelAndView modelAndView = new ModelAndView();
        TimeSheet timeSheet = timeSheetService.getById(id_timesheet);
        TimeSheetRow timeSheetRow = timeSheetService.getByRowId(id_row);
        Employee employee = timeSheetRow.getEmployee();
        modelAndView.addObject("timeSheet",timeSheet);
        modelAndView.addObject("employee",employee);
        modelAndView.addObject("timeSheetRow", timeSheetRow);
        modelAndView.addObject("id", id_row);
        modelAndView.setViewName("addDays");
        return modelAndView;
    }

    /**
     * Изменение  модели строки табеля (не используется, будем изменять строку, используя REST контроллер)
     * @param timeSheetRow Строка табеля
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/{id_row}",method = RequestMethod.POST)
    public ModelAndView editTimeSheetRow(@ModelAttribute("timeSheetRow") TimeSheetRow timeSheetRow) {
        ModelAndView modelAndView = new ModelAndView();
        timeSheetService.editTimeSheetRow(timeSheetRow);
        modelAndView.setViewName("redirect:/{id_department}/{id_timesheet}/");
        return modelAndView;
    }

    /**
     * Страница для добавления строки табеля (не используется, будем добавлять строку на фронте)
     * @param id_department id подразделения
     * @param id_timesheet id табеля
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/add",method = RequestMethod.GET)
    public ModelAndView addTimeSheetRowPage(@PathVariable String id_department, @PathVariable String id_timesheet) {
        ModelAndView modelAndView = new ModelAndView();
        Department department = departmentService.getById(id_department);
        List<Employee> employees = employeeService.getEmployeesByDepartment(department);
        modelAndView.addObject("employees", employees);
        modelAndView.addObject("id_department",id_department);
        modelAndView.addObject("id_timesheet",id_timesheet);
        //model.addAttribute("timeSheetRow",new TimeSheetRow());
        modelAndView.addObject("timeSheetRow",new TimeSheetRow());
        modelAndView.setViewName("addTimeSheetRow");
        return modelAndView;
    }

    /**
     * Добавление строки табеля в модель (не используется, будем добавлять строку, используя REST контроллер)
     * @param timeSheetRow
     * @param id_department
     * @param id_timesheet
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/add",method = RequestMethod.POST)
    public ModelAndView addTimeSheetRow(@ModelAttribute("TimeSheetRow") TimeSheetRow timeSheetRow,
                                        @PathVariable String id_department, @PathVariable int id_timesheet) {

        ModelAndView modelAndView = new ModelAndView();
        timeSheetRow.setTimeSheet(timeSheetService.getById(id_timesheet));
        //пока не будем здесь заполянть
        //timeSheetService.addTimeSheetRow(timeSheetRow);
        //пока закомментим, перекинем на заполнение дней
        //modelAndView.setViewName("redirect:/{id_department}/{id_timesheet}");
        modelAndView.addObject("timeSheetRow", timeSheetRow);
        String id_employee = timeSheetRow.getEmployee().getId();
        modelAndView.addObject("id_employee",id_employee);
        modelAndView.setViewName("redirect:/{id_department}/{id_timesheet}/add/{id_employee}");
        return modelAndView;
    }

    /**
     * Страница для добавления строки табеля (не используется, т.к. добавлять строку будет на фронте)
     * @param id_department id подразделения
     * @param id_timesheet id табеля
     * @param id_employee id сотрудника
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/add/{id_employee}", method = RequestMethod.GET)
    public ModelAndView addDaysPage(@PathVariable String id_department, @PathVariable int id_timesheet, @PathVariable String id_employee) {
        ModelAndView modelAndView = new ModelAndView();
        Employee employee= employeeService.getById(id_employee);
        TimeSheet timeSheet  = timeSheetService.getById(id_timesheet);
        Department department = timeSheet.getDepartment();
        HoursWorked hoursWorked = hoursWorkedService.get(employee, timeSheet.getPeriod());
        modelAndView.addObject("hoursWorked",hoursWorked);
        modelAndView.addObject("employee",employee);
        modelAndView.addObject("timeSheet",timeSheet);
        modelAndView.addObject("department",department);
        modelAndView.addObject("timeSheetRow",new TimeSheetRow());
        modelAndView.setViewName("addDays");
        return modelAndView;
    }

    /**
     * Добавление в модель строки табеля (не используется, т.к. строку будем добавлять через REST контроллер)
     * @param timeSheetRow строка табеля
     * @param id_timesheet id табеля
     * @param id_employee id сотрудника
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/add/{id_employee}", method = RequestMethod.POST)
    public ModelAndView addDays(@ModelAttribute("TimeSheetRow") TimeSheetRow timeSheetRow, @PathVariable int id_timesheet, @PathVariable String id_employee) {
        ModelAndView modelAndView = new ModelAndView();
        timeSheetRow.setTimeSheet(timeSheetService.getById(id_timesheet));
        timeSheetService.addTimeSheetRow(timeSheetRow);
        modelAndView.setViewName("redirect:/{id_department}/{id_timesheet}/");
        return modelAndView;
    }

    /**
     * Удаление строки табеля
     * @param id_row id строки для удаления
     * @return
     */
    @RequestMapping(value = "/{id_department}/{id_timesheet}/delete/{id_row}", method = RequestMethod.GET)
    public ModelAndView deleteTimeSheetRow(@PathVariable int id_row) {

        ModelAndView modelAndView = new ModelAndView();
        TimeSheetRow timeSheetRow = timeSheetService.getByRowId(id_row);
        timeSheetService.deleteTimeSheetRow(timeSheetRow);
        modelAndView.setViewName("redirect:/{id_department}/{id_timesheet}/");
        return modelAndView;

    }
}
