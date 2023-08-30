package ru.rsreu.malistova0206.dao;

import ru.rsreu.malistova0206.entity.UserGroups;
import ru.rsreu.malistova0206.entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private Connection connection;

    /**
     * Find user by login and password
     * @param login
     * @param password
     * @return
     * @throws SQLException
     */
    public Users findUser(String login, String password) throws SQLException {
        Users user = null;

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.user.find");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            user = new Users(result.getInt("user_id"),
                    new UserGroups(result.getInt("user_group_id"),
                            result.getString("group_name")),
                    result.getString("login"),
                    result.getString("user_password"),
                    result.getBoolean("has_access"));
        }
        System.out.println(user);
        if (user != null) {
            if (!user.getHasAccess()) {
                user = null;
            }
        }
        connection.close();

        return user;
    }

    /**
     * Get list of users
     * @return
     * @throws SQLException
     */
    public List<Users> selectUsers() throws SQLException {
        List<Users> users = new ArrayList<>();

        connection = ConnectionCreator.getConnection();
        Statement statement = connection.createStatement();
        String sql = Resourcer.getString("query.user.select");
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            users.add(new Users(result.getInt("user_id"),
                    new UserGroups(result.getInt("user_group_id"),
                            result.getString("group_name")),
                    result.getString("login"),
                    result.getString("user_password"),
                    result.getBoolean("has_access")));
        }
        connection.close();

        return users;
    }

    /**
     * Find user by id
     * @param userId
     * @return
     * @throws SQLException
     */
    public Users getUserById(int userId) throws SQLException {
        Users user = null;

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.user.getbyid");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userId);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            user = new Users(result.getInt("user_id"),
                    new UserGroups(result.getInt("user_group_id"),
                            result.getString("group_name")),
                    result.getString("login"),
                    result.getString("user_password"),
                    result.getBoolean("has_access"));
        }
        connection.close();

        return user;
    }

    /**
     * Update user's login and password
     * @param login
     * @param password
     * @param userId
     * @throws SQLException
     */
    public void updateUser(String login, String password, int userId) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.user.update");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login);
        statement.setString(2, password);
        statement.setInt(3, userId);
        statement.executeUpdate();

        connection.close();
    }

    /**
     * Add new user
     * @param roleId
     * @param login
     * @param password
     * @throws SQLException
     */
    public void addUser(int roleId, String login, String password) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.user.add");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, roleId);
        statement.setString(2, login);
        statement.setString(3, password);

        statement.executeUpdate();

        connection.close();
    }

    /**
     * Delete user
     * @param deletedUserId
     * @throws SQLException
     */
    public void deleteUser(int deletedUserId) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.user.delete");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, deletedUserId);
        statement.executeUpdate();

        connection.close();
    }

    /**
     * Get users of a specific group
     * @param userRole
     * @return
     * @throws SQLException
     */
    public List<Users> selectSpecificRole(String userRole) throws SQLException {
        List<Users> specUsers = new ArrayList<>();

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.user.selectspecific");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userRole);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            specUsers.add(new Users(result.getInt("user_id"),
                    result.getString("login")));
        }
        connection.close();

        return specUsers;
    }

    /**
     * Block and unblock user
     * @param blockedUser
     * @param access
     * @throws SQLException
     */
    public void blockUser(int blockedUser, boolean access) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.user.block");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setBoolean(1, access);
        statement.setInt(2, blockedUser);
        statement.executeUpdate();

        connection.close();
    }
}
