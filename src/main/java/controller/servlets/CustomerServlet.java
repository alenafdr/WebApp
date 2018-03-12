package controller.servlets;

import model.Customer;
import service.CustomerService;
import view.PageView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageView pageView = new PageView();
        pageView.showList(new Customer(), req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        CustomerService customerService = new CustomerService();
        Customer customer;
        int id;

        switch (action){
            case "insert":
                if (req.getParameter("name").equals("")) break;
                customer = new Customer(req.getParameter("name"));
                if(customerService.save(customer, req.getParameterValues("projects[]"))){
                    req.setAttribute("message", "Object has created");
                } else {
                    req.setAttribute("message", "Error, object has not created");
                }
                break;
            case "save":
                if (req.getParameter("name").equals("")) break;
                id = Integer.parseInt(req.getParameter("id"));
                customer = new Customer(req.getParameter("name"));
                if(customerService.update(id, customer, req.getParameterValues("projects[]"))){
                    req.setAttribute("message", "Object has updated");
                } else {
                    req.setAttribute("message", "Error, object has not updated");
                    System.out.println("id-" + id + " name-" + customer.getName());
                }
                break;
            case "edit":
                id = Integer.parseInt(req.getParameter("id"));
                customer = customerService.getById(id);
                req.setAttribute("nameObject", customer.getName());
                break;
            case "delete":
                id = Integer.parseInt(req.getParameter("id"));
                if(customerService.remove(customerService.getById(id))){
                    req.setAttribute("message", "Object has deleted");
                } else {
                    req.setAttribute("message", "Error, object has not deleted");
                }
                break;
        }
        PageView pageView = new PageView();
        pageView.showList(new Customer(), req,resp);
    }
}
