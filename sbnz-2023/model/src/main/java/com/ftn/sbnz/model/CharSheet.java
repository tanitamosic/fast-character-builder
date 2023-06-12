package com.ftn.sbnz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public CharSheet(Background background, Race race, CharClass charClass) {
        this.background = background;
        this.race = race;
        this.charClass = charClass;
    }

    

    
}
