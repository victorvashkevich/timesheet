package vvv.timesheet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="departments")
public class Department {

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_enterprise")
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user")
    private User user;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "department")
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

    public Enterprise getEnterprise() {
        return enterprise;
    }

    @Override
    public String toString() {
        return name;
    }
}
