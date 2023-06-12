package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
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

    public void getCharClass(ArrayList<PartyMemberDTO> party) {
        HashMap<Role, Integer> partyRoles = roleService.getPartyRoles(party);
        HashMap<Role, Double> neededRoles = roleService.getNeededRoles(partyRoles, party.size());
        HashMap<Skill, Double> neededSkills = skillService.getNeededSkills(party);
        HashMap<Subclass, Double> candidates = getCandidates(neededRoles, neededSkills);
        for (Subclass s : candidates.keySet()){
            System.out.println(s);
        }
    }

    private HashMap<Subclass, Double> getCandidates(HashMap<Role, Double> neededRoles, HashMap<Skill, Double> neededSkills) {
        HashMap<Subclass,Double> cr = roleService.getCandidatesRoles(neededRoles);
        HashMap<Subclass,Double> cs = skillService.getCandidatesSkills(neededSkills);
        return combineCandidates(cr,cs);
    }

    private HashMap<Subclass, Double> combineCandidates(HashMap<Subclass, Double> cr, HashMap<Subclass, Double> cs) {
        HashMap<Subclass, Double> candidates = new HashMap<>();
        for (Subclass r : cr.keySet()){
            if (candidates.containsKey(r)){
                candidates.put(r, candidates.get(r)+cr.get(r));
            } else{
                candidates.put(r,cr.get(r));
            }
        }
        for (Subclass r : cs.keySet()){
            if (candidates.containsKey(r)){
                candidates.put(r, candidates.get(r)+cr.get(r));
            } else{
                candidates.put(r,cr.get(r));
            }
        }
        candidates.remove(Subclass.NO_SUBCLASS);
        return candidates;
    }
}
