package com.ftn.sbnz.service;

import com.ftn.sbnz.model.PartyMemberDTO;
import com.ftn.sbnz.model.Role;
import com.ftn.sbnz.model.Skill;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ClassService {

    @Autowired
    KieContainer kieContainer;
    @Autowired
    SkillService skillService;
    @Autowired
    RoleService roleService;

    public void temp(ArrayList<PartyMemberDTO> party) {
        HashMap<Role, Integer> partyRoles = roleService.getPartyRoles(party);
        HashMap<Role, Double> neededRoles = roleService.getNeededRoles(partyRoles, party.size());
        HashMap<Skill, Double> neededSkills = skillService.getNeededSkills(party);
    }
}
