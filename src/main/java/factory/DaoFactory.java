package factory;

import dao.UsersDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

class DaoFactory {
    private static DaoFactory instance;
    private UsersDao usersDao;
    private Properties properties;

    static {
        instance = new DaoFactory();
    }
    private DaoFactory() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\Java\\HomeWork\\JDBC\\src\\main\\resources\\application.properties"));
            String daoClassName = properties.getProperty("users.dao.class");
            Constructor constructor = Class.forName(daoClassName).getConstructor(Connection.class);
            usersDao = (UsersDao) constructor.newInstance(ConnectionFactory.getInstance().getConnection());

        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public static DaoFactory getInstance() {
        return instance;
    }
}
