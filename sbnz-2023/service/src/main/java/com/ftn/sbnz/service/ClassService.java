package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
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

    private static final HashMap<String, List<String>> abilitySkills = new HashMap<>() {{
        put(Ability.STRENGTH.toString(), Arrays.asList(Skill.ATHLETICS.toString()));
        put(Ability.DEXTERITY.toString(), Arrays.asList(Skill.ACROBATICS.toString(), Skill.SLEIGH_OF_HAND.toString(), Skill.STEALTH.toString()));
        put(Ability.INTELLIGENCE.toString(), Arrays.asList(Skill.ARCANA.toString(), Skill.HISTORY.toString(), Skill.INVESTIGATION.toString(),  Skill.NATURE.toString(), Skill.RELIGION.toString()));
        put(Ability.WISDOM.toString(), Arrays.asList(Skill.ANIMAL_HANDLING.toString(), Skill.INSIGHT.toString(), Skill.MEDICINE.toString(),  Skill.PERCEPTION.toString(), Skill.SURVIVAL.toString()));
        put(Ability.CHARISMA.toString(), Arrays.asList(Skill.DECEPTION.toString(), Skill.INTIMIDATION.toString(), Skill.PERFORMANCE.toString(),  Skill.PERSUASION.toString()));
    }};

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
                if ((s.getCharClass().equals(CharClass.BARD) && new Random().nextBoolean()))
                    return s;
                else {
                    Integer randInd = new Random().nextInt(filteredCandidates.keySet().size());
                    Subclass rand = (Subclass) filteredCandidates.keySet().toArray()[randInd];
                    return rand;
                }
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
                candidates.put(r, candidates.get(r)+cs.get(r));
            } else{
                candidates.put(r,cs.get(r));
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
        return new CharSheet(charClass, subclass, proficiencies, abilityScores, subclass.getDisplayName());
    }

    private HashMap<Ability, Integer> getAbilityScores(CharClass charClass, ArrayList<Skill> proficiencies) {
        KieSession ksession = kieContainer.newKieSession("abilityKSession");
        HashMap<Ability, Integer> abilityScores = new HashMap<>();
        ksession.insert(abilityScores);

        for (Skill s: proficiencies) {
            ksession.insert(new AbilityContainer(s.toString(), charClass.toString()));
            for (String ability: abilitySkills.keySet()) {
                if (abilitySkills.get(ability).contains(s.toString()))
                {
                    ksession.insert(new AbilityContainer(ability, s.toString()));
                }
            }
        }
        ksession.fireAllRules();
        return abilityScores;
    }

    private ArrayList<Skill> getProficiencies(ArrayList<PartyMemberDTO> members, CharClass charClass ) {
        HashMap<Skill, Double> neededSkills = skillService.getNeededSkills(members);
        return skillService.getProficiencies(neededSkills, charClass);

    }
}
