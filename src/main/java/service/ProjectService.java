package service;

import model.Developer;
import model.Project;
import service.DAO.HibernateDeveloperDAO;
import service.DAO.HibernateProjectDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjectService implements GenericService<Project, Integer, String> {
    private HibernateProjectDAO jdbcProjectDAO;
    private HibernateDeveloperDAO hibernateDeveloperDAO;

    public ProjectService() {
        jdbcProjectDAO = new HibernateProjectDAO();
        hibernateDeveloperDAO = new HibernateDeveloperDAO();
    }

    @Override
    public boolean save(Project project, String[] ids) {
        Set<Developer> developers = new HashSet<>();
        for (String id : ids){
            int idInt = Integer.parseInt(id);
            developers.add(hibernateDeveloperDAO.getById(idInt));
        }
        project.setDevelopers(developers);
        return jdbcProjectDAO.save(project);
    }

    @Override
    public Project getById(Integer integer) {
        return jdbcProjectDAO.getById(integer);
    }

    @Override
    public List<Project> getAll() {
        return jdbcProjectDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Project project, String[] ids) {
        Set<Developer> developers = new HashSet<>();
        for (String id : ids){
            int idInt = Integer.parseInt(id);
            developers.add(hibernateDeveloperDAO.getById(idInt));
        }
        project.setDevelopers(developers);
        return jdbcProjectDAO.update(integer, project);
    }

    @Override
    public boolean remove(Project project) {
        return jdbcProjectDAO.remove(project);
    }

}
