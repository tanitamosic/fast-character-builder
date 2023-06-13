package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.max;

@Service
public class ClassService {

    @Autowired
    KieContainer kieContainer;
    @Autowired
    SkillService skillService;
    @Autowired
    RoleService roleService;
    @Autowired
    CampaignService campaignService;

    public Subclass getCharClass(PartyDTO party) {
        HashMap<Role, Integer> partyRoles = roleService.getPartyRoles(party.members);
        HashMap<Role, Double> neededRoles = roleService.getNeededRoles(partyRoles, party.members.size());
        HashMap<Skill, Double> neededSkills = skillService.getNeededSkills(party.members);
        HashMap<Subclass, Double> candidates = getCandidates(neededRoles, neededSkills);
        for (Subclass s : candidates.keySet()){
            System.out.println(s.getDisplayName());
        }
        HashMap<Subclass, Double> filteredCandidates = campaignService.filterCandidates(candidates,party.campaign);
        return highestPriorityCandidate(filteredCandidates);
    }

    private Subclass highestPriorityCandidate(HashMap<Subclass, Double> filteredCandidates) {
        Double maxPiority = max(filteredCandidates.values());
        for(Subclass s : filteredCandidates.keySet()){
            if(Objects.equals(filteredCandidates.get(s), maxPiority))
                return s;
        }
        return null;
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

    public CharSheet getCharSheet(PartyDTO mock) {
        Subclass subclass = getCharClass(mock);
        CharClass charClass = subclass.getCharClass();
        ArrayList<Skill> proficiencies = getProficiencies(mock.members, charClass);
        HashMap<Ability, Integer> abilityScores = getAbilityScores(charClass, proficiencies);
        return new CharSheet(charClass, subclass, proficiencies, abilityScores);
    }

    private HashMap<Ability, Integer> getAbilityScores(CharClass charClass, ArrayList<Skill> proficiencies) {
        return null;
        //BACKWARD
    }

    private ArrayList<Skill> getProficiencies(ArrayList<PartyMemberDTO> members, CharClass charClass ) {
        HashMap<Skill, Double> neededSkills = skillService.getNeededSkills(members);
        ArrayList<Skill> proficiencies = skillService.filterSkills(neededSkills, charClass);
        return new ArrayList<Skill>(proficiencies);

    }
}
