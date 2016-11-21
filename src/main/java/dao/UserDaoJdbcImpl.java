package dao;

import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class UserDaoJdbcImpl implements UsersDao {

    // language=SQL
    private static final String SQL_SELECT_USER ="SELECT * FROM group_member";
    // language=SQL
    private static final String SQL_UPDATE_USER = "UPDATE group_member SET name = ?, age = ?, city = ? WHERE id = ?";
    // language=SQL
    private static final String SQL_INSERT_USER = "INSERT group_member(id, name, age, city) VALUES(?, ?, ?,?)";
    // language=SQL
    private static final String SQL_SELECT_USER_BY_CITY = "SELECT * FROM group_member WHERE city=?";
    // language=SQL
    private static final String SQL_DELETE_USER = "DELETE * FROM group_member WHERE id = ?";

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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getCity());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public User findByCity(String city) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_USER_BY_CITY);
            int userId = resultSet.getInt("id");
            String userName = resultSet.getString("name");
            int userAge = resultSet.getInt("age");
            String userCity = resultSet.getString("city");
            return new User(userId,userName,userAge,userCity);
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
