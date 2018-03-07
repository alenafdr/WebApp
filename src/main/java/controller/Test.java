package controller;

import model.Skill;
import service.SkillService;

public class Test {
    public static void main(String[] args) {
        SkillService skillService = new SkillService();
        for (Skill skill : skillService.getAll()){
            System.out.println(skill);
        }
    }
}
