package vvv.timesheet.service;

import vvv.timesheet.model.Department;
import vvv.timesheet.model.TimeSheet;
import vvv.timesheet.model.TimeSheetRow;

import java.util.List;

public interface TimeSheetService {
    List<TimeSheet> getTimeSheetsByDepartment(Department department);
    TimeSheet getById(int id);
    TimeSheetRow getByRowId(int id);
    List<TimeSheetRow> getTimeSheetRows(TimeSheet timeSheet);
    void addTimeSheet(TimeSheet timeSheet);
    void deleteTimeSheet(TimeSheet timeSheet);
    void editTimeSheet(TimeSheet timeSheet);
    void addTimeSheetRow(TimeSheetRow timeSheetRow);
    void deleteTimeSheetRow(TimeSheetRow timeSheetRow);
    void editTimeSheetRow(TimeSheetRow timeSheetRow);
}
