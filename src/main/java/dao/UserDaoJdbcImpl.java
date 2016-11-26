package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;

public class UserDaoJdbcImpl implements UsersDao {

    // language=SQL
    private static final String SQL_SELECT_USER ="SELECT * FROM group_member";
    // language=SQL
    private static final String SQL_UPDATE_USER ="UPDATE group_member SET name = ?, age = ?, city = ? WHERE id =?";
    // language=SQL
    private static final String SQL_INSERT_USER ="INSERT INTO group_member (id, name, age, city) VALUES(?,?, ?, ?)";
    // language=SQL
    private static final String SQL_SELECT_USER_BY_CITY ="SELECT * FROM group_member WHERE city =?";
    // language=SQL
    private static final String SQL_DELETE_USER ="DELETE FROM group_member WHERE id = ?";

    private Connection connection;

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> findAll() {

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_USER);
            ArrayList<User> resultList = new ArrayList<User>();
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("city"));
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

    public ArrayList<User> findByCity(String city) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_CITY);
            preparedStatement.setString(1, city);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> resultList = new ArrayList<User>();
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("age"),resultSet.getString("city"));
                resultList.add(user);
            }
            return resultList;
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
