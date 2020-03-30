package vvv.timesheet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="departments")
public class Department {

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_enterprise")
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany(mappedBy = "department",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TimeSheet> timeSheets;

    @Id
    @Column(name="id")
    private String id;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public User getUser() {

        return user;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<TimeSheet> getTimeSheets() {
        return timeSheets;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    @Override
    public String toString() {
        return name;
    }
}
