package service;

import model.Company;
import model.Project;
import dao.HibernateCompanyDAO;
import dao.HibernateProjectDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CompanyService implements GenericService<Company, Integer, String> {

    private HibernateCompanyDAO hibernateCompanyDAO;
    private HibernateProjectDAO hibernateProjectDAO;

    public CompanyService() {
        hibernateCompanyDAO = new HibernateCompanyDAO();
        hibernateProjectDAO = new HibernateProjectDAO();
    }

    @Override
    public boolean save(Company company, String[] ids) {
        Set<Project> projects = new HashSet<>();
        for (String id : ids){
            int idInt = Integer.parseInt(id);
            projects.add(hibernateProjectDAO.getById(idInt));
        }
        company.setProjects(projects);
        return hibernateCompanyDAO.save(company);
    }

    @Override
    public Company getById(Integer integer) {
        return hibernateCompanyDAO.getById(integer);
    }

    @Override
    public List<Company> getAll() {
        return hibernateCompanyDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Company company, String[] ids) {
        Set<Project> projects = new HashSet<>();
        for (String id : ids){
            int idInt = Integer.parseInt(id);
            projects.add(hibernateProjectDAO.getById(idInt));
        }
        company.setProjects(projects);
        return hibernateCompanyDAO.update(integer, company);
    }

    @Override
    public boolean remove(Company company) {
        return hibernateCompanyDAO.remove(company);
    }

}
