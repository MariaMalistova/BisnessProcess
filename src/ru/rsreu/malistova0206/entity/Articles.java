package ru.rsreu.malistova0206.entity;

public class Articles {
    private int articleId;
    private Users author;
    private String text;
    private Steps step;
    private Users moderator;
    private Users editor;
    private Users corrector;

    /**
     * Getter for articleId
     * @return
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     * Setter for articleId
     * @param articleId
     */
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    /**
     * Getter for author
     * @return
     */
    public Users getAuthor() {
        return author;
    }

    /**
     * Setter for author
     * @param author
     */
    public void setAuthor(Users author) {
        this.author = author;
    }

    /**
     * Getter for text
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for step
     * @return
     */
    public Steps getStep() {
        return step;
    }

    /**
     * Setter for step
     * @param step
     */
    public void setStep(Steps step) {
        this.step = step;
    }

    /**
     * Getter for moderator
     * @return
     */
    public Users getModerator() {
        return moderator;
    }

    /**
     * Setter for moderator
     * @param moderator
     */
    public void setModerator(Users moderator) {
        this.moderator = moderator;
    }

    /**
     * Getter for editor
     * @return
     */
    public Users getEditor() {
        return editor;
    }

    /**
     * Setter for editor
     * @param editor
     */
    public void setEditor(Users editor) {
        this.editor = editor;
    }

    /**
     * Getter for corrector
     * @return
     */
    public Users getCorrector() {
        return corrector;
    }

    /**
     * Setter for corrector
     * @param corrector
     */
    public void setCorrector(Users corrector) {
        this.corrector = corrector;
    }

    /**
     * Constructor
     * @param articleId
     * @param author
     * @param text
     * @param step
     * @param moderator
     * @param editor
     * @param corrector
     */
    public Articles(int articleId, Users author, String text, Steps step, Users moderator, Users editor, Users corrector) {
        this.articleId = articleId;
        this.author = author;
        this.text = text;
        this.step = step;
        this.moderator = moderator;
        this.editor = editor;
        this.corrector = corrector;
    }
}
