package view;

import model.*;
import service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PageView {
    private static final String MAIN_JSP = "/main.jsp";

    public void showList(Object object , HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericService service;
        List<Object> objects = null;
        if (object instanceof Skill){
            service = new SkillService();
            objects = service.getAll();
            req.setAttribute("entity", "skill");
        } else if (object instanceof Developer) {
            service = new DeveloperService();
            objects = service.getAll();
            req.setAttribute("entity", "developer");
        } else if (object instanceof Project) {
            service = new ProjectService();
            objects = service.getAll();
            req.setAttribute("entity", "project");
        } else if (object instanceof Company) {
            service = new CompanyService();
            objects = service.getAll();
            req.setAttribute("entity", "company");
        } else if (object instanceof Customer) {
            service = new CustomerService();
            objects = service.getAll();
            req.setAttribute("entity", "customer");
        }
        req.setAttribute("objects", objects);
        RequestDispatcher view = req.getRequestDispatcher(MAIN_JSP);
        view.forward(req, resp);
    }
}
