package service;

import model.Customer;
import model.Project;
import DAO.HibernateCustomerDAO;
import DAO.HibernateProjectDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerService implements GenericService<Customer, Integer, String> {
    private HibernateCustomerDAO hibernateCustomerDAO;
    private HibernateProjectDAO hibernateProjectDAO;
    public CustomerService() {
        hibernateCustomerDAO = new HibernateCustomerDAO();
        hibernateProjectDAO = new HibernateProjectDAO();
    }

    @Override
    public boolean save(Customer customer, String[] ids) {
        Set<Project> projects = new HashSet<>();
        for (String id : ids){
            int idInt = Integer.parseInt(id);
            projects.add(hibernateProjectDAO.getById(idInt));
        }
        customer.setProjects(projects);
        return hibernateCustomerDAO.save(customer);
    }

    @Override
    public Customer getById(Integer integer) {
        return hibernateCustomerDAO.getById(integer);
    }

    @Override
    public List<Customer> getAll() {
        return hibernateCustomerDAO.getAll();
    }

    @Override
    public boolean update(Integer id, Customer customer, String[] ids) {
        Set<Project> projects = new HashSet<>();
        for (String idS : ids){
            int idInt = Integer.parseInt(idS);
            projects.add(hibernateProjectDAO.getById(idInt));
        }
        customer.setProjects(projects);
        return hibernateCustomerDAO.update(id, customer);
    }

    @Override
    public boolean remove(Customer customer) {
        return hibernateCustomerDAO.remove(customer);
    }

}
