package ru.rsreu.malistova0206.dao;

import ru.rsreu.malistova0206.entity.Steps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StepsDao {
    private Connection connection;

    /**
     * Get step by its id
     * @param stepId
     * @return
     * @throws SQLException
     */
    public Steps getStepById(int stepId) throws SQLException {
        Steps step = null;

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.steps.getbyid");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, stepId);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            step = new Steps(result.getInt("step_id"),
                    result.getString("step_role"));
        }
        connection.close();

        return step;
    }
}
