package dao;

import model.User;

import java.util.List;

public interface UsersDao {

    List<User> findAll();
    void update(User user);
    void save(User user);
    User find(int id);
    void delete(int id);
}