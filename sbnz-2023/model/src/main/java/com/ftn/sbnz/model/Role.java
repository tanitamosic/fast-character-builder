package com.ftn.sbnz.model;

public enum Role {
    // TODO: add priorities
    BLASTER(3),
    CONTROLLER(3),
    DEFENDER(4),
    FACE(3),
    HEALER(5),
    LIBRARIAN(2),
    SCOUT(3),
    STRIKER(3),
    SUPPORT(5),
    UTILITY(4);

    private final Integer priority;

    private Role(Integer priority){
        this.priority = priority;
    }

    public Integer getPriority(){ return this.priority; }
}
