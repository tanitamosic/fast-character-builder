package com.ftn.sbnz.model;

public enum Skill {
    ACROBATICS(4),
    ANIMAL_HANDLING(1),
    ARCANA(4),
    ATHLETICS(4),
    DECEPTION(4),
    HISTORY(2),
    INSIGHT(5),
    INTIMIDATION(3),
    INVESTIGATION(6),
    MEDICINE(1),
    NATURE(2),
    PERCEPTION(6),
    PERFORMANCE(1),
    PERSUASION(5),
    RELIGION(2),
    SLEIGHT_OF_HAND(5),
    STEALTH(6),
    SURVIVAL(3),
    THIEVES_TOOLS(0);

    private final Integer priority;

    private Skill(Integer priority){
        this.priority = priority;
    }

    public Integer getPriority(){ return this.priority; }
}
