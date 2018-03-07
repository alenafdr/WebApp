<%@ page import="java.util.List" %>
<%@ page import="model.Skill" %>
<%@ page import="service.SkillService" %>
<%@ page import="java.util.Enumeration" %>
<%!
    public List<Skill> getAllSkill(){
        SkillService skillService = new SkillService();
        return skillService.getAll();
    }


%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add skill</title>
</head>
<body>

    <h3>Введите значения для skill</h3>
    <form action="add-skill" method="post">
        <table border = "0">
            <tr>
                <td><b>Name</b></td>
                <td><input type = "text" name = "name" size = "20"/></td>
            </tr>

            <tr>
                <td colspan = "2"><input type = "submit" value = "Add"/></td>
            </tr>
        </table>

    </form>

    <table>

    <% Enumeration en = request.getHeaderNames();
        while(en.hasMoreElements()) {
            String name = (String)en.nextElement();
            String value = request.getParameter(name);%>
    <tr>
        <td>name <%=name%></td>
        <td>value <%=value%></td>
    </tr>
        <%}
    %>
    </table>
    <table>
        <tr>
            <td>id</td>
            <td>name</td>
        </tr>
        <%for (Skill skill : getAllSkill()){%>
        <tr>
            <td><%=skill.getId()%></td>
            <td><%=skill.getName()%></td>
        </tr>
        <%}
        %>
    </table>
</body>
</html>
