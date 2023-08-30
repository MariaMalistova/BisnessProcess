package ru.rsreu.malistova0206.entity;

public class UserGroups {
    private int userGroupId;
    private String groupName;

    /**
     * Getter for userGroupId
     * @return
     */
    public int getUserGroupId() {
        return userGroupId;
    }

    /**
     * Setter for userGroupId
     * @param userGroupId
     */
    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    /**
     * Getter for groupName
     * @return
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Setter for groupName
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Constructor
     * @param userGroupId
     * @param groupName
     */
    public UserGroups(int userGroupId, String groupName) {
        this.userGroupId = userGroupId;
        this.groupName = groupName;
    }
}
