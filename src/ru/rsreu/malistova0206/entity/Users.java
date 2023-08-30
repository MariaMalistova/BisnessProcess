package ru.rsreu.malistova0206.entity;

public class Users {
    private int userId;
    private UserGroups userGroup;
    private String login;
    private String userPassword;
    private Boolean hasAccess;

    /**
     * Getter for userId
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Setter for userId
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Getter for userGroup
     * @return
     */
    public UserGroups getUserGroup() {
        return userGroup;
    }

    /**
     * Setter for userGroup
     * @param role
     */
    public void setUserGroup(UserGroups role) {
        this.userGroup = role;
    }

    /**
     * Getter for login
     * @return
     */
    public String getLogin() {
        return login;
    }

    /**
     * Setter for login
     * @param login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Getter for userPassword
     * @return
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Setter for userPassword
     * @param userPassword
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Getter for hasAccess
     * @return
     */
    public Boolean getHasAccess() {
        return hasAccess;
    }

    /**
     * Setter for hasAccess
     * @param hasAccess
     */
    public void setHasAccess(Boolean hasAccess) {
        this.hasAccess = hasAccess;
    }

    /**
     * Constructor with all attributes
     * @param userId
     * @param userGroup
     * @param login
     * @param userPassword
     * @param hasAccess
     */
    public Users(int userId, UserGroups userGroup, String login, String userPassword, Boolean hasAccess) {
        this.userId = userId;
        this.userGroup = userGroup;
        this.login = login;
        this.userPassword = userPassword;
        this.hasAccess = hasAccess;
    }

    /**
     * Constructor with id and login
     * @param userId
     * @param login
     */
    public Users(int userId, String login) {
        this.userId = userId;
        this.login = login;
    }
}
