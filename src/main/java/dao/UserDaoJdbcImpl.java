package dao;

import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UsersDao {

    // language=SQL
    private static final String SQL_SELECT_USER ="SELECT * FROM group_member";

    private Connection connection;

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> findAll() {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_USER);

            List<User> resultList = new ArrayList<User>();
            while (resultSet.next()){
                int userId = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                int userAge = resultSet.getInt("age");
                String userCity = resultSet.getString("city");
                User user = new User(userId,userName,userAge,userCity);
                resultList.add(user);
            }
            return resultList;
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(User user) {

    }

    public void save(User user) {

    }

    public User find(int id) {
        return null;
    }

    public void delete(int id) {

    }
}
