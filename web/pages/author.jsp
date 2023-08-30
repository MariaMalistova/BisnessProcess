<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author</title>
</head>
<body>
<form name="authorForm" method="POST" action="journal?action=authoraddarticle">
    <input type="hidden" name="command" value="login" />
    Добавить статью:<br/>
    <textarea type="text" name="articletext" value="" cols="40" rows="5" maxlength="5000"></textarea>
    <input type="submit" value="Добавить"/>
</form><hr/>
<c:forEach var="article" items="${articles}">
    <c:out value="${article.getArticleId()}"/><br>
    <label>Автор:</label>
    <c:out value="${article.getAuthor().getLogin()}"/><br>
    <label>Модератор:</label>
    <c:out value="${article.getModerator().getLogin()}"/><br>
    <label>Редактор:</label>
    <c:out value="${article.getEditor().getLogin()}"/><br>
    <label>Корректор:</label>
    <c:out value="${article.getCorrector().getLogin()}"/><br>
    <c:out value="${article.getText()}"/><br>
    <hr/>
</c:forEach>
<form action="journal?action=logout" method="POST">
    <input type="submit" value="Logout" name="logout" class="button"/>
</form>
</body>
</html>