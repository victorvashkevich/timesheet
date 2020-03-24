package vvv.timesheet.dao;

import vvv.timesheet.model.Employee;
import vvv.timesheet.model.HoursWorked;

public interface HoursWorkedDAO {
    HoursWorked getById(int id);
    HoursWorked get(Employee employee, String period);
}
