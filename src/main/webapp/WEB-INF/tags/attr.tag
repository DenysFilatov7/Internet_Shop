<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="condition" required="true" type="java.lang.Boolean" rtexprvalue="true" %>
<%--<% if(condition){%>--%>
<%--    Condition is True--%>
<%--<% } else { %>--%>
<%--    Condition is False--%>
<%--<% } %>--%>
<c:choose>
    <c:when test="${condition}">Condition is true</c:when>
    <c:otherwise>Condition is false</c:otherwise>
</c:choose>