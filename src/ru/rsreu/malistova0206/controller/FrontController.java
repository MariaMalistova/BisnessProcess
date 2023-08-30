package ru.rsreu.malistova0206.controller;

import ru.rsreu.malistova0206.dao.ArticleDao;
import ru.rsreu.malistova0206.dao.UserGroupDao;
import ru.rsreu.malistova0206.dao.UsersDao;
import ru.rsreu.malistova0206.entity.Articles;
import ru.rsreu.malistova0206.entity.UserGroups;
import ru.rsreu.malistova0206.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FrontController extends HttpServlet {
    public final static String ADMIN_URL = "/02_06_malistova/journal?action=admin";
    public final static String LOGIN_URL = "/02_06_malistova/journal?action=login";
    public final static String AUTHOR_URL = "/02_06_malistova/journal?action=author";
    public final static String MODERATOR_URL = "/02_06_malistova/journal?action=moderator";
    public final static String EDITS_URL = "/02_06_malistova/journal?action=edits";

    HttpSession session = null;

    private Users user;
    private UsersDao userDao = new UsersDao();
    private UserGroupDao groupDao = new UserGroupDao();
    private ArticleDao articleDao = new ArticleDao();

    /**
     * Urls for the method POST
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        session = request.getSession();
        if (action != null) {
            switch (action) {
                case "login":
                    try {
                        String login = request.getParameter("login");
                        String password = request.getParameter("password");
                        user = userDao.findUser(login, password);
                        if (user != null) {
                            String role = user.getUserGroup().getGroupName();
                            session.setAttribute("name", user.getUserId());
                            session.setAttribute("userGroup", role);
                            switch (role) {
                                case "Administrator":
                                    response.sendRedirect(ADMIN_URL);
                                    break;
                                case "Author":
                                    response.sendRedirect(AUTHOR_URL);
                                    break;
                                case "Moderator":
                                    response.sendRedirect(MODERATOR_URL);
                                    break;
                                case "Editor":
                                case "Corrector":
                                    response.sendRedirect(EDITS_URL);
                                    break;
                                default:
                                    response.sendRedirect(LOGIN_URL);
                                    break;
                            }
                        } else {
                            response.sendRedirect(LOGIN_URL);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "adminedituser":
                    String editedUserId = request.getParameter("getEditedId");
                    try {
                        List<Users> users = userDao.selectUsers();
                        List<UserGroups> roles = groupDao.selectRoles();
                        Users editedUser = userDao.getUserById(Integer.parseInt(editedUserId));

                        request.setAttribute("users", users);
                        request.setAttribute("roles", roles);
                        request.setAttribute("editedUser", editedUser);
                        request.getRequestDispatcher("pages/admin.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "useredited":
                    String newUserLogin = request.getParameter("login");
                    String newPassword = request.getParameter("password");
                    String userId = request.getParameter("editedUserId");
                    try {
                        userDao.updateUser(newUserLogin, newPassword, Integer.parseInt(userId));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect(ADMIN_URL);
                    break;
                case "adminadduser":
                    try {
                        String login = request.getParameter("login");
                        String password = request.getParameter("password");
                        String roleName = request.getParameter("role");
                        UserGroups role = groupDao.getUserGroupName(roleName);
                        userDao.addUser(role.getUserGroupId(), login, password);
                        response.sendRedirect(ADMIN_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "admindeleteuser":
                    String deletedUserId = request.getParameter("getDeletedId");
                    try {
                        userDao.deleteUser(Integer.parseInt(deletedUserId));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect(ADMIN_URL);
                    break;
                case "authoraddarticle":
                    try {
                        String text = request.getParameter("articletext");
                        int author = (int) session.getAttribute("name");
                        articleDao.addArticle(author, text);
                        response.sendRedirect(AUTHOR_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "assignmoder":
                    try {
                        String article = request.getParameter("chosenarticle");
                        String moder = request.getParameter("moder");
                        articleDao.assignModer(article, moder);
                        response.sendRedirect(ADMIN_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "blockuser":
                    try {
                        String blockedUser = request.getParameter("getBlockedId");
                        userDao.blockUser(Integer.parseInt(blockedUser), false);
                        response.sendRedirect(MODERATOR_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "unblockuser":
                    try {
                        String unblockedUser = request.getParameter("getUnblockedId");
                        userDao.blockUser(Integer.parseInt(unblockedUser), true);
                        response.sendRedirect(MODERATOR_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "deletearticle":
                    String deletedArticle = request.getParameter("deletedarticle");
                    try {
                        articleDao.deleteArticle(Integer.parseInt(deletedArticle));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect(MODERATOR_URL);
                    break;
                case "assigneditorcorrector":
                    try {
                        String article = request.getParameter("chosenarticle");
                        String editor = request.getParameter("edit");
                        String corrector = request.getParameter("correct");
                        articleDao.assignEditorCorrector(article, editor, corrector);
                        response.sendRedirect(MODERATOR_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "editarticle":
                    String articleId = request.getParameter("getEditedId");
                    String articleText = request.getParameter("articletext");
                    try {
                        articleDao.updateArticle(articleText, Integer.parseInt(articleId));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    response.sendRedirect(EDITS_URL);
                    break;
                case "articleedits":
                    String editedArticleId = request.getParameter("getEditedArticleId");
                    try {
                        Users user = userDao.getUserById((int)session.getAttribute("name"));
                        List<Articles> articles = articleDao.selectArticles(user.getUserId(), user.getUserGroup().getGroupName());
                        Articles editedArticle = articleDao.getArticleById(Integer.parseInt(editedArticleId));
                        request.setAttribute("editedArticle", editedArticle);
                        request.setAttribute("articles", articles);
                        request.getRequestDispatcher("pages/edits.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "assignnext":
                    try {
                        String editedDone = request.getParameter("getEditedDoneId");
                        articleDao.assignNext(articleDao.getArticleById(Integer.parseInt(editedDone)));
                        response.sendRedirect(EDITS_URL);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "logout":
                    session.setAttribute("name", null);
                    session.setAttribute("userGroup", null);
                    response.sendRedirect(LOGIN_URL);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Urls for the method GET
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        session = request.getSession();
        if (action != null) {
            switch (action) {
                case "login":
                    request.getRequestDispatcher("pages/login.jsp").forward(request, response);
                    break;
                case "admin":
                    try {
                        List<Users> users = userDao.selectUsers();
                        Users user = userDao.getUserById((int)session.getAttribute("name"));
                        List<Articles> articles = articleDao.selectArticles(user.getUserId(), user.getUserGroup().getGroupName());
                        List<UserGroups> roles = groupDao.selectRoles();
                        List<Users> moders = userDao.selectSpecificRole("Moderator");
                        request.setAttribute("users", users);
                        request.setAttribute("roles", roles);
                        request.setAttribute("articles", articles);
                        request.setAttribute("editedUser", null);
                        request.setAttribute("moders", moders);
                        request.setAttribute("session", session.getAttribute("name"));
                        request.getRequestDispatcher("pages/admin.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "author":
                    try {
                        List<Articles> articles = articleDao.selectArticles(user.getUserId(), user.getUserGroup().getGroupName());
                        request.setAttribute("articles", articles);
                        request.getRequestDispatcher("pages/author.jsp").forward(request, response);
                    } catch (SQLException e) {
                      e.printStackTrace();
                    }
                    break;
                case "moderator":
                    try {
                        List<Users> users = userDao.selectUsers();
                        Users user = userDao.getUserById((int)session.getAttribute("name"));
                        List<Articles> articles = articleDao.selectArticles(user.getUserId(), user.getUserGroup().getGroupName());
                        List<Users> editors = userDao.selectSpecificRole("Editor");
                        List<Users> correctors = userDao.selectSpecificRole("Corrector");
                        request.setAttribute("session", session.getAttribute("name"));
                        request.setAttribute("users", users);
                        request.setAttribute("articles", articles);
                        request.setAttribute("editors", editors);
                        request.setAttribute("correctors", correctors);
                        request.getRequestDispatcher("pages/moderator.jsp").forward(request, response);
                    } catch (SQLException e) {
                      e.printStackTrace();
                    }
                    break;
                case "edits":
                    try {
                        Users user = userDao.getUserById((int)session.getAttribute("name"));
                        List<Articles> articles = articleDao.selectArticles(user.getUserId(), user.getUserGroup().getGroupName());
                        System.out.println(articles);
                        request.setAttribute("session", session.getAttribute("name"));
                        request.setAttribute("articles", articles);
                        request.getRequestDispatcher("pages/edits.jsp").forward(request, response);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
