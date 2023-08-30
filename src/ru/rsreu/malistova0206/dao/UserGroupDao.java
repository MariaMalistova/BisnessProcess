package ru.rsreu.malistova0206.dao;

import ru.rsreu.malistova0206.entity.UserGroups;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {
    Connection connection;

    /**
     * Get user's group name
     * @param name
     * @return
     * @throws SQLException
     */
    public UserGroups getUserGroupName(String name) throws SQLException {
        UserGroups userGroup = null;

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.userGroup.byName");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            userGroup = new UserGroups(result.getInt("user_group_id"),
                    result.getString("group_name"));
        }
        connection.close();

        return userGroup;
    }

    /**
     * Get list of user's groups
     * @return
     * @throws SQLException
     */
    public List<UserGroups> selectRoles() throws SQLException {
        List<UserGroups> roles = new ArrayList<>();

        connection = ConnectionCreator.getConnection();
        Statement statement = connection.createStatement();
        String sql = Resourcer.getString("query.userGroup.select");
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            roles.add(new UserGroups(result.getInt("user_group_id"),
                    result.getString("group_name")));
        }
        connection.close();

        return roles;
    }
}
