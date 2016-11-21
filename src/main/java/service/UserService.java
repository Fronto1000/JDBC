package service;

import model.Auto;
import model.User;
import java.util.List;

public interface UserService {
    boolean isRegistered(String name);
    List<User> getUsersByCity(String cityName);
    List<Auto> getAutoByCity(List<User> users);
}
