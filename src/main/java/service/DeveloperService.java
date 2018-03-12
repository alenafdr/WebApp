package service;

import model.Developer;
import model.Skill;
import DAO.HibernateDeveloperDAO;
import DAO.HibernateSkillDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperService implements GenericService<Developer, Integer, String> {

    private HibernateDeveloperDAO hibernateDeveloperDAO = new HibernateDeveloperDAO();
    private HibernateSkillDAO hibernateSkillDAO = new HibernateSkillDAO();

    @Override
    public boolean save(Developer developer, String[] idSkills) {
        Set<Skill> skills = new HashSet<>();
        for (String id : idSkills){
            int idInt = Integer.parseInt(id);
            skills.add(hibernateSkillDAO.getById(idInt));
        }
        developer.setSkills(skills);
        return hibernateDeveloperDAO.save(developer);
    }

    @Override
    public Developer getById(Integer integer) {
        return hibernateDeveloperDAO.getById(integer);
    }

    @Override
    public List<Developer> getAll() {
        return hibernateDeveloperDAO.getAll();
    }

    @Override
    public boolean update(Integer integer, Developer developer, String[] idSkills) {
        Set<Skill> skills = new HashSet<>();
        for (String id : idSkills){
            int idInt = Integer.parseInt(id);
            skills.add(hibernateSkillDAO.getById(idInt));
        }
        developer.setSkills(skills);
        return hibernateDeveloperDAO.update(integer, developer);
    }

    @Override
    public boolean remove(Developer developer) {
        return hibernateDeveloperDAO.remove(developer);
    }

}
