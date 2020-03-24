package vvv.timesheet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vvv.timesheet.model.Department;
import vvv.timesheet.model.TimeSheet;
import vvv.timesheet.model.TimeSheetRow;

import java.util.List;

@Repository
public class TimeSheetDAOImpl implements TimeSheetDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TimeSheet> getTimeSheetsByDepartment(Department department) {
        Session session = sessionFactory.getCurrentSession();
        List<TimeSheet> timeSheets = session.createQuery("from TimeSheet  where department =:department").setParameter("department", department).list();
        return timeSheets;
    }

    @Override
    public TimeSheet getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TimeSheet.class,id);
    }

    @Override
    public List<TimeSheetRow> getTimeSheetRows(TimeSheet timeSheet) {
        Session session = sessionFactory.getCurrentSession();
        List<TimeSheetRow> timeSheetRows = session.createQuery("from TimeSheetRow where timeSheet = :timesheet").setParameter("timesheet",timeSheet).list();
        return timeSheetRows;
    }

    @Override
    public void addTimeSheet(TimeSheet timeSheet) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(timeSheet);
    }

    @Override
    public TimeSheetRow getByRowId(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(TimeSheetRow.class,id);
    }

    @Override
    public void deleteTimeSheet(TimeSheet timeSheet) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(timeSheet);
    }

    @Override
    public void editTimeSheet(TimeSheet timeSheet) {
        Session session = sessionFactory.getCurrentSession();
        session.update(timeSheet);
    }

    @Override
    public void addTimeSheetRow(TimeSheetRow timeSheetRow) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(timeSheetRow);
    }

    @Override
    public void deleteTimeSheetRow(TimeSheetRow timeSheetRow) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(timeSheetRow);
    }

    @Override
    public void editTimeSheetRow(TimeSheetRow timeSheetRow) {
        Session session = sessionFactory.getCurrentSession();
        session.update(timeSheetRow);
    }
}
