package service;

import model.Skill;
import dao.HibernateSkillDAO;

import java.util.List;

public class SkillService implements GenericService<Skill, Integer, String> {
    private HibernateSkillDAO jdbcSkillDAO = new HibernateSkillDAO();

    @Override
    public boolean save(Skill skill, String[] idObjects) {
        return jdbcSkillDAO.save(skill);
    }

    @Override
    public Skill getById(Integer id) {
        return jdbcSkillDAO.getById(id);
    }

    @Override
    public List<Skill> getAll() {
        return jdbcSkillDAO.getAll();
    }

    @Override
    public boolean update(Integer id, Skill skill, String[] idObjects) {
        return jdbcSkillDAO.update(id, skill);
    }

    @Override
    public boolean remove(Skill skill) {
        return jdbcSkillDAO.remove(skill);
    }

}
