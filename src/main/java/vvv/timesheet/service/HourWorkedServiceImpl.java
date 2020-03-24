package vvv.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vvv.timesheet.dao.HoursWorkedDAO;
import vvv.timesheet.model.Employee;
import vvv.timesheet.model.HoursWorked;

import javax.transaction.Transactional;

@Service
public class HourWorkedServiceImpl implements  HoursWorkedService{
    private HoursWorkedDAO hoursWorkedDAO;

    @Autowired
    public void setHoursWorkedDAO(HoursWorkedDAO hoursWorkedDAO) {
        this.hoursWorkedDAO = hoursWorkedDAO;
    }

    @Override
    @Transactional
    public HoursWorked getById(int id) {
        return hoursWorkedDAO.getById(id);
    }

    @Override
    @Transactional
    public HoursWorked get(Employee employee, String period) {
        return hoursWorkedDAO.get(employee,period);
    }
}
