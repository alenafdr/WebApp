<%@ page import="java.util.List" %>
<%@ page import="service.ProjectService" %>
<%@ page import="model.Project" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%
    String action = request.getParameter("action");
    String nameObject = request.getParameter("nameObject");
    String id = request.getParameter("id");
    if (action == null) action = "";

    ProjectService projectService = new ProjectService();
    List<Project> projects = projectService.getAll();
%>
<h3>Введите значения для company</h3>
<%if (action.equals("edit")){%>
<form method="post">
    <input name = "action" type="hidden" value="save"/>
    <input name = "id" type="hidden" value="<%=id%>"/>
    <table border = "0">
        <tr>
            <td><b>Name</b></td>
            <td><input type = "text" name = "name" size = "20" value="<%=nameObject%>" /></td>
        </tr>
        <tr>
            <td><b>Projects</b></td>
            <td>
                <ul>
                    <c:forEach items="<%=projects%>" var="project">
                        <li><input name = "projects[]" value="${project.id}" type="checkbox" />${project.name}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Save"/></td>
        </tr>
    </table>
</form>
<form method="get">
    <tr>
        <td colspan = "2"><input type = "submit" value = "Cancel"/></td>
    </tr>
</form>
<%} else {%>
<form method="post">
    <input name = "action" type="hidden" value="insert"/>
    <table border = "0">
        <tr>
            <td><b>Name</b></td>
            <td><input type = "text" name = "name" size = "20"/></td>
        </tr>
        <tr>
            <td><b>Projects</b></td>
            <td>
                <ul>
                <c:forEach items="<%=projects%>" var="project">
                    <li><input name = "projects[]" value="${project.id}" type="checkbox" />${project.name}</li>
                </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "Add"/></td>
        </tr>
    </table>
</form>
<%}%>