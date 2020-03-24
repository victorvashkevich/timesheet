package vvv.timesheet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "domain")
    private String domain;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Department> departments;

    public String getId() {
        return id;
    }

    public String getDomain() {
       return domain;
    }

    public String getName() {
        return name;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
