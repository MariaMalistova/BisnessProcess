package ru.rsreu.malistova0206.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static String host = "localhost";
    private static int port = 1521;
    private static String sid = "xe";
    private static String user = "system";
    private static String password = "admin";
    private static String url = String.format("jdbc:oracle:thin:@%s:%d:%s", host, port, sid);
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
