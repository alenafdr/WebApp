package controller.servlets;

import model.Developer;
import service.DeveloperService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeveloperServlet extends CoreServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showList(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        DeveloperService developerService = new DeveloperService();

        switch (action){
            case "insert":
                if (req.getParameter("name").equals("")) break;
                Developer developer = new Developer(req.getParameter("name"));
                if(developerService.save(developer, req.getParameterValues("skills[]"))){
                    req.setAttribute("message", "Object has created");
                } else {
                    req.setAttribute("message", "Error, object has not created");
                }
                break;
            case "save":
                if (req.getParameter("name").equals("")) break;
                int idSave = Integer.parseInt(req.getParameter("id"));
                Developer developerSave = new Developer(req.getParameter("name"));
                if(developerService.update(idSave, developerSave, req.getParameterValues("skills[]"))){
                    req.setAttribute("message", "Object has updated");
                } else {
                    req.setAttribute("message", "Error, object has not updated");
                }
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Developer developerEdit = developerService.getById(idEdit);
                req.setAttribute("nameObject", developerEdit.getName());
                break;
            case "delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                if(developerService.remove(developerService.getById(idDelete))){
                    req.setAttribute("message", "Object has deleted");
                } else {
                    req.setAttribute("message", "Error, object has not deleted");
                }
                break;
        }
        showList(req,resp);
    }

    public void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        DeveloperService developerService = new DeveloperService();
        List<Developer> developers = developerService.getAll();
        req.setAttribute("objects", developers);
        req.setAttribute("entity", "developer");
        RequestDispatcher view = req.getRequestDispatcher(MAIN_JSP);
        view.forward(req, resp);
    }
}
