package vvv.timesheet.service;

import vvv.timesheet.model.Employee;
import vvv.timesheet.model.HoursWorked;

public interface HoursWorkedService {
    HoursWorked getById(int id);
    HoursWorked get(Employee employee, String period);
}
