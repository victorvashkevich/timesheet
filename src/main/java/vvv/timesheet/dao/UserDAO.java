package vvv.timesheet.dao;

import vvv.timesheet.model.User;

import java.util.List;

public interface UserDAO {

    User getById(String id);
    User getByName(String name);
    List<User> getUsers();
}
