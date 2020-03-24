package vvv.timesheet.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @OneToMany(mappedBy = "post")
    private List<Employee> employees;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
