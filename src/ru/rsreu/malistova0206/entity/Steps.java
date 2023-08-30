package ru.rsreu.malistova0206.entity;

public class Steps {
    private int stepId;
    private String stepName;

    /**
     * Getter for stepId
     * @return
     */
    public int getStepId() {
        return stepId;
    }

    /**
     * Setter for stepId
     * @param stepId
     */
    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    /**
     * Getter for stepName
     * @return
     */
    public String getStepName() {
        return stepName;
    }

    /**
     * Setter for stepName
     * @param stepName
     */
    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    /**
     * Constructor
     * @param stepId
     * @param stepName
     */
    public Steps(int stepId, String stepName) {
        this.stepId = stepId;
        this.stepName = stepName;
    }
}
