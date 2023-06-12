package com.ftn.sbnz.model;

import java.util.ArrayList;
import java.util.List;

public class PartyMemberDTO {
    public CharClass charClass;
    public Subclass subclass;
    public List<Skill> skills;

    public PartyMemberDTO(CharClass charClass, Subclass subclass, ArrayList<Skill> list) {
        this.charClass = charClass;
        this.subclass = subclass;
        this.skills = list;
    }
}
