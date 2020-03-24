package vvv.timesheet.service;

import vvv.timesheet.model.User;

import java.util.List;

public interface UserService {
    User getById(String id);
    User getByName(String name);
    List<User> getUsers();
}