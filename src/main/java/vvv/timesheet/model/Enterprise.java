package vvv.timesheet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="enterprises")
public class Enterprise {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @OneToMany(mappedBy = "enterprise")
    private List<Department> departments;

    @OneToMany(mappedBy = "enterprise")
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    @Override
    public String toString() {
        return name;
    }
}
