package factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;
    private Properties properties;

    static {
        instance = new ConnectionFactory();
    }
    private ConnectionFactory() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Java\\HomeWork\\JDBC\\src\\main\\resources\\application.properties"));
            String driverName = properties.getProperty("database.driver");
            String url = properties.getProperty("database.url");
            String name = properties.getProperty("database.name");
            String password = properties.getProperty("database.password");

            Class.forName(driverName);
            connection = DriverManager.getConnection(url,name,password);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }
}
