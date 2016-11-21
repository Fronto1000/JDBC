package dao;

import model.User;

import java.util.List;

public interface UsersDao {

    List<User> findAll();
    void update(User user);
    void save(User user);
    User findByCity(String city);
    void delete(int id);
}