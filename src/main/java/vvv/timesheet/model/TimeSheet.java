package vvv.timesheet.model;

import javax.persistence.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "timesheetdoc")

public class TimeSheet {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name="period")
    private String period;

    @ManyToOne(fetch = FetchType.EAGER) //ставим EAGER т.к. если LAZY, то в методе get не будет тянуть зависимые поля
    @JoinColumn(name = "id_department")
    private Department department;

    @OneToMany(mappedBy = "timeSheet", fetch = FetchType.EAGER)
    private List<TimeSheetRow> timeSheetRows;

    public int getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public Department getDepartment() {
        return department;
    }

    public String getPeriod() {
        return period;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<TimeSheetRow> getTimeSheetRows() {
        return timeSheetRows;
    }

    public String getEmployeesString() {

        StringBuilder stringBuilder = new StringBuilder(" ");

        for (TimeSheetRow timeSheetRow:timeSheetRows) {
            stringBuilder.append(timeSheetRow.getEmployee().getShortName()).append(" ");
        }

        return stringBuilder.toString();
    }

    public String getStringPeriod() {

        String year = period.substring(0,4);
        int numberOfMonth = Integer.parseInt(period.substring(5,7));

        Month month = Month.of(numberOfMonth);
        Locale loc = Locale.forLanguageTag("ru");

        return month.getDisplayName(TextStyle.FULL_STANDALONE, loc)+" "+year;
    }

    public int getNumberOfDays() {

        Month month = Month.of(Integer.parseInt(period.substring(5,7)));
        return month.maxLength();
    }
}
