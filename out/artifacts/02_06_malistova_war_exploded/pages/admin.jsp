<%@ page import="ru.rsreu.malistova0206.entity.UserGroups" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.rsreu.malistova0206.entity.Users" %><%--
  Created by IntelliJ IDEA.
  User: malis
  Date: 24.05.2021
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"

         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h3>Users</h3>

<table>
    <tr>
        <th>ID</th>
        <th>Роль</th>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Доступ</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}">
        <c:if test ="${editedUser.getUserId() != user.getUserId()}">
        <tr>
            <td>
                <c:out value="${user.getUserId()}"/>
            </td>
            <td>
                <c:out value="${user.getUserGroup().getGroupName()}"/>
            </td>
            <td>
                <c:out value="${user.getLogin()}"/>
            </td>
            <td>
                <c:out value="${user.getUserPassword()}"/>
            </td>
            <td>
                <c:out value="${user.getHasAccess()}"/>
            </td>
            <td>
                <form action="journal?action=adminedituser" method="POST">
                    <input type="submit" value="Редактировать" name="edit" class="button"/>
                    <input type="hidden" value="${user.getUserId()}" name="getEditedId"/>
                </form>
            </td>
            <td>
            <c:if test ="${session != user.getUserId()}">
                <form action="journal?action=admindeleteuser" method="POST">
                    <input type="submit" value="Удалить" name="delete" class="button"/>
                    <input type="hidden" value="${user.getUserId()}" name="getDeletedId"/>
                </form>
            </c:if>
            </td>
        </tr>
        </c:if>
    </c:forEach>
</table>

<c:if test ="${editedUser != null}">
    <div class="form">
        <form action="journal?action=useredited" method="POST">
            <label>Логин:</label><br>
            <input type="text" name="login" value="${editedUser.getLogin()}"></input><br>
            <label>Пароль:</label><br>
            <input type="text" name="password" value="${editedUser.getUserPassword()}"></input><br>
            <br>
            <input type="submit" value="Edit" class="button"></input>
            <input type="hidden" value="${editedUser.getUserId()}" name="editedUserId"/>
        </form>

        <!--  <input type="submit" value="Cancel" class="button"><a href="/jobrequest?action=admin"></input> -->

    </div>
</c:if>

<c:if test ="${editedUser == null}">
    <div class="form">
        <form action="journal?action=adminadduser" method="POST">
            <h3>Добавить пользователя</h3>
            <label>Логин:</label><br>
            <input type="text" name="login"></input><br>
            <label>Пароль:</label><br>
            <input type="text" name="password"></input><br>
            <label>Роль:</label><br>
            <select name="role">
                <%
                    List<UserGroups> roles = (List<UserGroups>) request.getAttribute("roles");
                    if (roles != null) {
                        for (UserGroups role : roles) {
                            out.println("<option>" + role.getGroupName() + "</option>");
                        }
                    } //else out.println("<p></p>");
                %>
            </select>
            <br>
            <input type="submit" value="Добавить" class="button"></input>
        </form>
    </div>
</c:if>

<c:forEach var="article" items="${articles}">
    <c:out value="${article.getArticleId()}"/><br>
    <c:out value="${article.getAuthor().getLogin()}"/><br>
    <c:out value="${article.getText()}"/><br>
    <div class="form">
        <form action="journal?action=assignmoder" method="POST">
            <label>Назначить модератора: </label>
            <select name="moder">
                <%
                    List<Users> moders = (List<Users>) request.getAttribute("moders");
                    if (moders != null) {
                        for (Users user : moders) {
                            out.println("<option>" + user.getLogin() + "</option>");
                        }
                    } //else out.println("<p></p>");
                %>
            </select>
            <input type="submit" value="Назначить" class="button"></input>
            <input type="hidden" value="${article.getArticleId()}" name="chosenarticle"/>
        </form>
    </div>
    <hr/>
</c:forEach>

<form action="journal?action=logout" method="POST">
    <input type="submit" value="Logout" name="logout" class="button"/>
</form>
</body>
</html>
