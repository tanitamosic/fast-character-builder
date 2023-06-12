package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/dnd/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping("/party")
    public ResponseEntity<String> postParty(@RequestBody PartyDTO dto){
        ArrayList<PartyMemberDTO> party = new ArrayList<>();
        party.add(new PartyMemberDTO(CharClass.BARBARIAN, Subclass.NO_SUBCLASS,
                new ArrayList<Skill>(Arrays.asList(Skill.ATHLETICS, Skill.ACROBATICS, Skill.NATURE, Skill.INTIMIDATION))));
        party.add(new PartyMemberDTO(CharClass.CLERIC, Subclass.TEMPEST,
                new ArrayList<Skill>(Arrays.asList(Skill.MEDICINE, Skill.HISTORY, Skill.INSIGHT, Skill.RELIGION))));
        classService.getCharClass(party);
        return ResponseEntity.ok("sve ok");
    }

}
