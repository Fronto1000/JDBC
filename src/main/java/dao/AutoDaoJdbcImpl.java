package dao;

import model.Auto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoDaoJdbcImpl implements AutoDao{

    // language=SQL
    private static final String SQL_SELECT_AUTO="SELECT * FROM auto";
    // language=SQL
    private final String SQL_UPDATE_AUTO = "UPDATE auto SET member_id = ? WHERE id = ?";

    private Connection connection;

    public AutoDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Auto> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_AUTO);
            List<Auto> resultList = new ArrayList<Auto>();
            while (resultSet.next()){
                int autoId = resultSet.getInt("id");
                String autoModel = resultSet.getString("model");
                int autoMileage = resultSet.getInt("mileage");
                int autoMemberId = resultSet.getInt("member_id");
                Auto auto = new Auto(autoId,autoModel,autoMileage,autoMemberId);
                resultList.add(auto);
            }
            return resultList;
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void update(Auto auto) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_AUTO);
            preparedStatement.setInt(1, auto.getMemberId());
            preparedStatement.setInt(2, auto.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void save(Auto auto) {

    }

    public Auto find(int id) {
        return null;
    }

    public void delete(int id) {

    }
}
