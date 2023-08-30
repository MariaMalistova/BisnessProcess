<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: malis
  Date: 28.05.2021
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editing</title>
</head>
<body>
<c:if test ="${articles.size() == 0}">
    <h1>No articles</h1>
</c:if>
<c:if test ="${editedArticle != null}">
    <div class="form">
        <form action="journal?action=editarticle" method="POST">
            <c:out value="${editedArticle.getArticleId()}"/><br>
            <c:out value="${editedArticle.getAuthor().getLogin()}"/><br>
            <textarea type="text" name="articletext" value="" cols="40" rows="5" maxlength="5000">${editedArticle.getText()}</textarea>
            <br>
            <input type="submit" value="Save" class="button">
            <input type="hidden" value="${editedArticle.getArticleId()}" name="getEditedId"/>
        </form>

        <!--  <input type="submit" value="Cancel" class="button"><a href="/jobrequest?action=admin"></input> -->

    </div>
</c:if>
<c:forEach var="article" items="${articles}">
    <c:if test ="${editedArticle.getArticleId() != article.getArticleId()}">
        <c:out value="${article.getArticleId()}"/><br>
        <c:out value="${article.getAuthor().getLogin()}"/><br>
        <c:out value="${article.getText()}"/><br>
        <form action="journal?action=articleedits" method="POST">
            <input type="submit" value="Edit" name="editarticle" class="button"/>
            <input type="hidden" value="${article.getArticleId()}" name="getEditedArticleId"/>
        </form>
        <form action="journal?action=assignnext" method="POST">
            <input type="submit" value="Завершить редактирование" name="editdone" class="button"/>
            <input type="hidden" value="${article.getArticleId()}" name="getEditedDoneId"/>
        </form>
    </c:if>
    <hr/>
</c:forEach>
<form action="journal?action=logout" method="POST">
    <input type="submit" value="Logout" name="logout" class="button"/>
</form>
</body>
</html>
