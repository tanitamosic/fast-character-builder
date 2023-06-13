package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharSheet {
    Background background;
    Race race;
    CharClass charClass;
    Subclass subclass;
    List<Skill> proficiencies;
    HashMap<Ability, Integer> abilityScores;
    String classString;

    public CharSheet(Background background, Race race, CharClass charClass) {
        this.background = background;
        this.race = race;
        this.charClass = charClass;
    }

    public CharSheet(CharClass charClass, Subclass subclass, ArrayList<Skill> proficiencies,
                     HashMap<Ability, Integer> abilityScores, String classString){
        this.charClass = charClass;
        this.subclass = subclass;
        this.proficiencies = proficiencies;
        this.abilityScores = abilityScores;
        this.classString = classString;
    }

    

    
}
