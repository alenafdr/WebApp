package controller.servlets;

import model.Skill;
import service.SkillService;
import view.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SkillsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageView pageView = new PageView();
        pageView.showList(new Skill(), req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        SkillService skillService = new SkillService();
        switch (action){
            case "insert":
                if (req.getParameter("name").equals("")) break;
                if(skillService.save(new Skill(req.getParameter("name")), null)){
                    req.setAttribute("message", "Object has created");
                } else {
                    req.setAttribute("message", "Error, object has not created");
                }
                break;
            case "save":
                if (req.getParameter("name").equals("")) break;
                int idSave = Integer.parseInt(req.getParameter("id"));
                if(skillService.update(idSave, new Skill(req.getParameter("name")), null)){
                    req.setAttribute("message", "Object has updated");
                } else {
                    req.setAttribute("message", "Error, object has not updated");
                }
                break;
            case "edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Skill skill = skillService.getById(idEdit);
                req.setAttribute("nameObject", skill.getName());
                break;
            case "delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                if(skillService.remove(skillService.getById(idDelete))){
                    req.setAttribute("message", "Object has deleted");
                } else {
                    req.setAttribute("message", "Error, object has not deleted");
                }
                break;
        }
        PageView pageView = new PageView();
        pageView.showList(new Skill(), req,resp);
    }
}
