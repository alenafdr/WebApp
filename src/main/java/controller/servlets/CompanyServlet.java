package controller.servlets;

import model.Company;
import service.CompanyService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CompanyServlet extends CoreServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showList(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CompanyService companyService = new CompanyService();
        Company company;
        int id;

        switch (action){
            case "insert":
                if (req.getParameter("name").equals("")) break;
                company = new Company(req.getParameter("name"));
                if(companyService.save(company, req.getParameterValues("projects[]"))){
                    req.setAttribute("message", "Object has created");
                } else {
                    req.setAttribute("message", "Error, object has not created");
                }
                break;
            case "save":
                if (req.getParameter("name").equals("")) break;
                id = Integer.parseInt(req.getParameter("id"));
                company = new Company(req.getParameter("name"));
                if(companyService.update(id, company, req.getParameterValues("projects[]"))){
                    req.setAttribute("message", "Object has updated");
                } else {
                    req.setAttribute("message", "Error, object has not updated");
                }
                break;
            case "edit":
                id = Integer.parseInt(req.getParameter("id"));
                company = companyService.getById(id);
                req.setAttribute("nameObject", company.getName());
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if(companyService.remove(companyService.getById(id))){
                    req.setAttribute("message", "Object has deleted");
                } else {
                    req.setAttribute("message", "Error, object has not deleted");
                }
                break;
        }
        showList(req,resp);
    }

    public void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        CompanyService companyService = new CompanyService();
        List<Company> companies = companyService.getAll();
        req.setAttribute("objects", companies);
        req.setAttribute("entity", "company");
        RequestDispatcher view = req.getRequestDispatcher(MAIN_JSP);
        view.forward(req, resp);
    }
}
