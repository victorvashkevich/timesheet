package vvv.timesheet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vvv.timesheet.dao.TimeSheetDAO;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.TimeSheet;
import vvv.timesheet.model.TimeSheetRow;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

    private TimeSheetDAO timeSheetDAO;

    @Autowired
    public void setTimeSheetDAO(TimeSheetDAO timeSheetDAO) {
        this.timeSheetDAO = timeSheetDAO;
    }

    @Override
    @Transactional
    public List<TimeSheet> getTimeSheetsByDepartment(Department department) {
        return timeSheetDAO.getTimeSheetsByDepartment(department);
    }

    @Override
    @Transactional
    public TimeSheet getById(int id) {
        return  timeSheetDAO.getById(id);
    }

    @Override
    @Transactional
    public List<TimeSheetRow> getTimeSheetRows(TimeSheet timeSheet) {
        return timeSheetDAO.getTimeSheetRows(timeSheet);
    }

    @Override
    @Transactional
    public void addTimeSheet(TimeSheet timeSheet) {

        StringBuilder stringBuilder = new StringBuilder(timeSheet.getPeriod());
        stringBuilder.replace(8,10,"01");
        timeSheet.setPeriod(stringBuilder.toString());

        timeSheetDAO.addTimeSheet(timeSheet);
    }

    @Override
    @Transactional
    public void deleteTimeSheet(TimeSheet timeSheet) {
        timeSheetDAO.deleteTimeSheet(timeSheet);
    }

    @Override
    @Transactional
    public void editTimeSheet(TimeSheet timeSheet) {
        timeSheetDAO.editTimeSheet(timeSheet);
    }

    @Override
    @Transactional
    public void addTimeSheetRow(TimeSheetRow timeSheetRow) {
        timeSheetDAO.addTimeSheetRow(timeSheetRow);
    }

    @Override
    @Transactional
    public void deleteTimeSheetRow(TimeSheetRow timeSheetRow) {
        timeSheetDAO.deleteTimeSheetRow(timeSheetRow);
    }

    @Override
    @Transactional
    public void editTimeSheetRow(TimeSheetRow timeSheetRow) {
        timeSheetDAO.editTimeSheetRow(timeSheetRow);
    }

    @Override
    @Transactional
    public TimeSheetRow getByRowId(int id) {
        return timeSheetDAO.getByRowId(id);
    }
}
