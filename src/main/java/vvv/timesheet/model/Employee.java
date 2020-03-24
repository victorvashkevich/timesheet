package vvv.timesheet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_department")
    private Department department;

    @OneToMany(mappedBy = "employee")
    private List<TimeSheetRow> timeSheetRows;


    @Column(name = "code")
    private String code;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public Post getPost() {
        return post;
    }

    public Department getDepartment() {
        return department;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return name;
    }
}
