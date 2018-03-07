package controller.servlets;

import model.Project;
import service.ProjectService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProjectServlet extends CoreServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showList(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ProjectService projectService = new ProjectService();
        Project project;
        int id;
        switch (action){
            case "insert":
                if (req.getParameter("name").equals("")) break;
                project = new Project(req.getParameter("name"));
                if(projectService.save(project, req.getParameterValues("developers[]"))){
                    req.setAttribute("message", "Object has created");
                } else {
                    req.setAttribute("message", "Error, object has not created");
                }
                break;
            case "save":
                if (req.getParameter("name").equals("")) break;
                id = Integer.parseInt(req.getParameter("id"));
                project = new Project(req.getParameter("name"));
                if(projectService.update(id, project, req.getParameterValues("developers[]"))){
                    req.setAttribute("message", "Object has updated");
                } else {
                    req.setAttribute("message", "Error, object has not updated");
                }
                break;
            case "edit":
                id = Integer.parseInt(req.getParameter("id"));
                Project projectEdit = projectService.getById(id);
                req.setAttribute("nameObject", projectEdit.getName());
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if(projectService.remove(projectService.getById(id))){
                    req.setAttribute("message", "Object has deleted");
                } else {
                    req.setAttribute("message", "Error, object has not deleted");
                }
                break;
        }
        showList(req,resp);
    }

    public void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        ProjectService projectService = new ProjectService();
        List<Project> projects = projectService.getAll();
        req.setAttribute("objects", projects);
        req.setAttribute("entity", "project");
        RequestDispatcher view = req.getRequestDispatcher(MAIN_JSP);
        view.forward(req, resp);
    }
}
