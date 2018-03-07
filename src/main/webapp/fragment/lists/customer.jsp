<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Projects</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${objects}" var="object">
        <tr>
            <td><c:out value="${object.id}" /></td>
            <td><c:out value="${object.name}" /></td>
            <td><c:out value="${object.projects}" /></td>
            <td><form method="post">
                <input name="action" value="edit" type="hidden"/>
                <input name="id" value="${object.id}" type="hidden"/>
                <input name="nameObject" value="${object.name}" type="hidden"/>
                <input type="submit" value="edit">
            </form></td>
            <td><form method="post">
                <input name="action" value="delete" type="hidden"/>
                <input name="id" value="${object.id}" type="hidden"/>
                <input type="submit" value="delete">
            </form></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
