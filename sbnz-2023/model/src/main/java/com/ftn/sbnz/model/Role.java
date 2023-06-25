package com.ftn.sbnz.model;

public enum Role {
    BLASTER(3),
    CONTROLLER(3),
    DEFENDER(4),
    FACE(3),
    HEALER(5),
    LIBRARIAN(1),
    SCOUT(2),
    STRIKER(3),
    SUPPORT(5),
    UTILITY(4);

    private final Integer priority;

    private Role(Integer priority){
        this.priority = priority;
    }

    public Integer getPriority(){ return this.priority; }
}
