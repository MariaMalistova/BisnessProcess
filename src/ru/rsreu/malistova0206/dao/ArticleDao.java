package ru.rsreu.malistova0206.dao;

import ru.rsreu.malistova0206.entity.Articles;
import ru.rsreu.malistova0206.entity.Steps;
import ru.rsreu.malistova0206.entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private Connection connection;

    /**
     * Add new article
     * @param author
     * @param text
     * @throws SQLException
     */
    public void addArticle(int author, String text) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.article.add");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, author);
        statement.setString(2, text);

        statement.executeUpdate();

        connection.close();
    }

    /**
     * Get list of articles
     * @param userId
     * @param role
     * @return
     * @throws SQLException
     */
    public List<Articles> selectArticles(int userId, String role) throws SQLException {
        List<Articles> articles = new ArrayList<>();
        String sql = null;
        Statement statement;
        PreparedStatement pstatement;
        ResultSet result = null;
        connection = ConnectionCreator.getConnection();
        if (role.equals("Administrator")) {
            statement = connection.createStatement();
            sql = Resourcer.getString("query.articles.selectAdmin");
            result = statement.executeQuery(sql);
        } else  if (role.equals("Author")) {
            statement = connection.createStatement();
            sql = Resourcer.getString("query.articles.selectdone");
            result = statement.executeQuery(sql);
            } else {
                switch (role) {
                    case "Moderator":
                        sql = Resourcer.getString("query.articles.selectModer");
                        break;
                    case "Editor":
                        sql = Resourcer.getString("query.articles.assigneditor");
                        break;
                    case "Corrector":
                        sql = Resourcer.getString("query.articles.assigncorrector");
                        break;
                    default:
                        break;
                }
                pstatement = connection.prepareStatement(sql);
                pstatement.setInt(1, userId);
                result = pstatement.executeQuery();

            }

        while (result.next()) {
            articles.add(new Articles(result.getInt("article_id"),
                    new Users(result.getInt("author"),
                            result.getString("author_login")),
                    result.getString("text"),
                    new Steps(result.getInt("step"),
                            result.getString("step_role")),
                    new Users(result.getInt("moderator"),
                            result.getString("moder")),
                    new Users(result.getInt("editor"),
                            result.getString("edit")),
                    new Users(result.getInt("corrector"),
                            result.getString("correct"))));
        }
        connection.close();

        return articles;
    }

    /**
     * Add attribute "Moderator" to an article
     * @param article
     * @param moder
     * @throws SQLException
     */
    public void assignModer(String article, String moder) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.article.assignmoder");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, moder);
        statement.setInt(2, Integer.parseInt(article));

        statement.executeUpdate();

        connection.close();
    }

    /**
     * Delete article
     * @param deletedArticle
     * @throws SQLException
     */
    public void deleteArticle(int deletedArticle) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.article.delete");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, deletedArticle);
        statement.executeUpdate();

        connection.close();
    }

    /**
     * Add attributes "Editor" and "Corrector" to an article
     * @param article
     * @param editor
     * @param corrector
     * @throws SQLException
     */
    public void assignEditorCorrector(String article, String editor, String corrector) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.article.assigneditorcorrector");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, editor);
        statement.setString(2, corrector);
        statement.setInt(3, Integer.parseInt(article));

        statement.executeUpdate();

        connection.close();
    }

    /**
     * Update article's text
     * @param text
     * @param articleId
     * @throws SQLException
     */
    public void updateArticle(String text, int articleId) throws SQLException {
        connection = ConnectionCreator.getConnection();

        String sql = Resourcer.getString("query.article.update");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, text);
        statement.setInt(2, articleId);
        statement.executeUpdate();

        connection.close();
    }

    /**
     * Get article by its id
     * @param articleId
     * @return
     * @throws SQLException
     */
    public Articles getArticleById(int articleId) throws SQLException {
        Articles article = null;

        connection = ConnectionCreator.getConnection();
        String sql = Resourcer.getString("query.article.getbyid");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, articleId);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            article = new Articles(result.getInt("article_id"),
                    new Users(result.getInt("author"),
                            result.getString("author_login")),
                    result.getString("text"),
                    new Steps(result.getInt("step"),
                            result.getString("step_role")),
                    new Users(result.getInt("moderator"),
                            result.getString("moder")),
                    new Users(result.getInt("editor"),
                            result.getString("edit")),
                    new Users(result.getInt("corrector"),
                            result.getString("correct")));
        }
        connection.close();

        return article;
    }

    /**
     * Change article's step
     * @param article
     * @throws SQLException
     */
    public void assignNext(Articles article) throws SQLException {
        connection = ConnectionCreator.getConnection();
        String articleStep = article.getStep().getStepName();
        String sql = Resourcer.getString("query.articles.assignnext");
        PreparedStatement statement = connection.prepareStatement(sql);
        String nextStep = null;
        switch (articleStep) {
            case "Editing":
                nextStep = "Correcting";
                break;
            case "Correcting":
                nextStep = "Ready";
                break;
            default:
                break;
        }

        statement.setString(1, nextStep);
        statement.setInt(2, article.getArticleId());
        statement.executeUpdate();

        connection.close();
    }
}
