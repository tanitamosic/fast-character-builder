package fastbuilder.app.model.subclass;

public enum Skill {
    ACROBATICS(4),
    ANIMAL_HANDLING(2),
    ARCANA(3),
    ATHLETICS(4),
    DECEPTION(4),
    HISTORY(3),
    INSIGHT(5),
    INTIMIDATION(3),
    INVESTIGATION(6),
    MEDICINE(3),
    NATURE(2),
    PERCEPTION(6),
    PERFORMANCE(2),
    PERSUASION(5),
    RELIGION(3),
    SLEIGH_OF_HAND(5),
    STEALTH(6),
    SURVIVAL(3),
    THIEVES_TOOLS(0);

    private final Integer priority;

    private Skill(Integer priority){
        this.priority = priority;
    }

    public Integer getPriority(){ return this.priority; }
}
