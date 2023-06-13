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
        PartyDTO mock = mockParty();
        CharSheet charSheet = classService.getCharSheet(mock);
        return ResponseEntity.ok("sve ok");
    }

    private PartyDTO mockParty() {
        PartyDTO party = new PartyDTO();
        ArrayList<PartyMemberDTO> members = new ArrayList<>();
        CampaignDTO camp = new CampaignDTO();
        camp.magic = Amount.LOW;
        camp.tech = Amount.MID;
        camp.dark = Amount.FULL;
        party.campaign = camp;
        party.members = members;
        members.add(new PartyMemberDTO(CharClass.BARBARIAN, Subclass.NO_SUBCLASS,
                new ArrayList<Skill>(Arrays.asList(Skill.ATHLETICS, Skill.ACROBATICS, Skill.NATURE, Skill.INTIMIDATION))));
        members.add(new PartyMemberDTO(CharClass.CLERIC, Subclass.TEMPEST,
                new ArrayList<Skill>(Arrays.asList(Skill.MEDICINE, Skill.HISTORY, Skill.INSIGHT, Skill.RELIGION))));
        return party;
    }

}
