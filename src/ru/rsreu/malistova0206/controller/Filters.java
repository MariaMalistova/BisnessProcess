package ru.rsreu.malistova0206.controller;

import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters implements Filter{
    public final static String ADMIN_URL = "/02_06_malistova/journal?action=admin";
    public final static String LOGIN_URL = "/02_06_malistova/journal?action=login";
    public final static String AUTHOR_URL = "/02_06_malistova/journal?action=author";
    public final static String MODERATOR_URL = "/02_06_malistova/journal?action=moderator";
    public final static String EDITS_URL = "/02_06_malistova/journal?action=edits";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = ((HttpServletRequest) request).getSession();

        List<String> adminActions = new ArrayList<>();
        adminActions.add("admin");
        adminActions.add("adminedituser");
        adminActions.add("useredited");
        adminActions.add("adminadduser");
        adminActions.add("admindeleteuser");
        adminActions.add("assignmoder");

        List<String> authorActions = new ArrayList<>();
        authorActions.add("author");
        authorActions.add("authoraddarticle");

        List<String> moderActions = new ArrayList<>();
        moderActions.add("moderator");
        moderActions.add("blockuser");
        moderActions.add("unblockuser");
        moderActions.add("deletearticle");
        moderActions.add("assigneditorcorrector");

        List<String> editsActions = new ArrayList<>();
        editsActions.add("edits");
        editsActions.add("editarticle");
        editsActions.add("articleedits");
        editsActions.add("assignnext");

        Map<String, List> users = new HashMap<>();
        users.put("Administrator", adminActions);
        users.put("Moderator", moderActions);
        users.put("Author", authorActions);
        users.put("Editor", editsActions);
        users.put("Corrector", editsActions);

        Map<String, String> urls = new HashMap<>();
        urls.put("Administrator", ADMIN_URL);
        urls.put("Moderator", MODERATOR_URL);
        urls.put("Author", AUTHOR_URL);
        urls.put("Editor", EDITS_URL);
        urls.put("Corrector", EDITS_URL);

        String userGroup = (String) session.getAttribute("userGroup");
        System.out.println("action");
        System.out.println(req.getParameter("action"));
        System.out.println(session.getAttribute("userGroup"));


        if (req.getParameter("action").equals("login") || req.getParameter("action").equals("logout")) {
            chain.doFilter(request, response);
            System.out.println("11111");
            return;
        }
        if (session.getAttribute("userGroup") != null) {

            if (users.get(userGroup).indexOf(req.getParameter("action")) >= 0) {
                System.out.println("22222");
                chain.doFilter(request, response);
            } else {
                System.out.println("33333");
                res.sendRedirect(urls.get(userGroup));
            }
        } else {
            System.out.println("44444");
            res.sendRedirect(LOGIN_URL);
        }
    }
}
