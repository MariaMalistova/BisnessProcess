<%@ page import="ru.rsreu.malistova0206.entity.Users" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: malis
  Date: 26.05.2021
  Time: 23:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moderator</title>
</head>
<body>
<h3>Users</h3>

<table>
    <tr>
        <th>ID</th>
        <th>Роль</th>
        <th>Логин</th>
        <th>Доступ</th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${users}">
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
                    <c:out value="${user.getHasAccess()}"/>
                </td>
                <td>
                    <c:if test ="${session != user.getUserId() && user.getUserGroup().getGroupName() != 'Administrator'}">
                        <c:if test ="${user.getHasAccess()}">
                            <form action="journal?action=blockuser" method="POST">
                                <input type="submit" value="Заблокировать" name="block" class="button"/>
                                <input type="hidden" value="${user.getUserId()}" name="getBlockedId"/>
                            </form>
                        </c:if>
                        <c:if test ="${!user.getHasAccess()}">
                            <form action="journal?action=unblockuser" method="POST">
                                <input type="submit" value="Разблокировать" name="unblock" class="button"/>
                                <input type="hidden" value="${user.getUserId()}" name="getUnblockedId"/>
                            </form>
                        </c:if>
                    </c:if>
                </td>
            </tr>
    </c:forEach>
</table>
<hr>
<c:forEach var="article" items="${articles}">
    <c:out value="${article.getArticleId()}"/><br>
    <c:out value="${article.getAuthor().getLogin()}"/><br>
    <c:out value="${article.getText()}"/><br>

    <form action="journal?action=deletearticle" method="POST">
        <input type="submit" value="Удалить" class="button"></input>
        <input type="hidden" value="${article.getArticleId()}" name="deletedarticle"/>
    </form>

    <form action="journal?action=assigneditorcorrector" method="POST">
        <label>Назначить редактора: </label>
        <select name="edit">
            <%
                List<Users> edits = (List<Users>) request.getAttribute("editors");
                if (edits != null) {
                    for (Users user : edits) {
                        out.println("<option>" + user.getLogin() + "</option>");
                    }
                } //else out.println("<p></p>");
            %>
        </select>
        <label>Назначить корректора: </label>
        <select name="correct">
            <%
                List<Users> correctors = (List<Users>) request.getAttribute("correctors");
                if (edits != null) {
                    for (Users user : correctors) {
                        out.println("<option>" + user.getLogin() + "</option>");
                    }
                } //else out.println("<p></p>");
            %>
        </select>
        <input type="submit" value="Назначить" class="button"></input>
        <input type="hidden" value="${article.getArticleId()}" name="chosenarticle"/>
    </form>
    <hr/>
</c:forEach>
<form action="journal?action=logout" method="POST">
    <input type="submit" value="Logout" name="logout" class="button"/>
</form>
</body>
</html>
