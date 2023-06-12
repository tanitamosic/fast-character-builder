package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class SkillService {
    
    private ArrayList<CharClassModel> data;
    public SkillService(){
        InputStream template = SkillService.class.getResourceAsStream("/class/party_skills.drt");
        data = new ArrayList<>();

        data.add(new CharClassModel(CharClass.ARTIFICER.getEnumString(), "ARCANA, HISTORY, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, "));
        data.add(new CharClassModel(CharClass.BARBARIAN.getEnumString(), "ANIMAL_HANDLING, ATHLETICS, INTIMIDATION, NATURE, PERCEPTION, SURVIVAL, "));
        data.add(new CharClassModel(CharClass.BARD.getEnumString(), "ACROBATICS, ANIMAL_HANDLING, ARCANA, ATHLETICS, DECEPTION, HISTORY, INSIGHT, INTIMIDATION, INVESTIGATION, MEDICINE, NATURE, PERCEPTION, PERFORMANCE, PERSUASION, RELIGION, SLEIGH_OF_HAND, STEALTH, SURVIVAL, "));
        data.add(new CharClassModel(CharClass.CLERIC.getEnumString(), "HISTORY, INSIGHT, MEDICINE, PERSUASION, RELIGION, "));
        data.add(new CharClassModel(CharClass.DRUID.getEnumString(), "ANIMAL_HANDLING, ARCANA, INSIGHT, MEDICINE, NATURE, PERCEPTION, RELIGION, SURVIVAL, "));
        data.add(new CharClassModel(CharClass.FIGHTER.getEnumString(), "ACROBATICS, ANIMAL_HANDLING, ATHLETICS, HISTORY, INSIGHT, INTIMIDATION, PERCEPTION, SURVIVAL, "));
        data.add(new CharClassModel(CharClass.MONK.getEnumString(), "ACROBATICS, ATHLETICS, HISTORY, INSIGHT, RELIGION, STEALTH, "));
        data.add(new CharClassModel(CharClass.PALADIN.getEnumString(), "ATHLETICS, INSIGHT, INTIMIDATION, MEDICINE, PERSUASION, RELIGION, "));
        data.add(new CharClassModel(CharClass.RANGER.getEnumString(), "ANIMAL_HANDLING, ATHLETICS, INSIGHT, INVESTIGATION, NATURE, PERCEPTION, STEALTH, SURVIVAL, "));
        data.add(new CharClassModel(CharClass.ROGUE.getEnumString(), "ACROBATICS, ATHLETICS, DECEPTION, INSIGHT, INTIMIDATION, INVESTIGATION, PERCEPTION, PERFORMANCE, PERSUASION, SLEIGH_OF_HAND, STEALTH, "));
        data.add(new CharClassModel(CharClass.SORCERER.getEnumString(), "ARCANA, DECEPTION, INSIGHT, INTIMIDATION, PERSUASION, RELIGION, "));
        data.add(new CharClassModel(CharClass.WARLOCK.getEnumString(), "ARCANA, DECEPTION, HISTORY, INTIMIDATION, INVESTIGATION, NATURE, RELIGION, "));
        data.add(new CharClassModel(CharClass.WIZARD.getEnumString(), "ARCANA, HISTORY, INSIGHT, INVESTIGATION, MEDICINE, RELIGION, "));

    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }

    public HashMap<Skill, Double> getNeededSkills(ArrayList<PartyMemberDTO> party) {
        HashMap<Skill, Double> ret = new HashMap<>();
        Skill[] allSkills = Skill.values();
        HashMap<Skill, Integer> partySkills = getPartySkills(party);

        for(Skill r : allSkills){
            if (partySkills.containsKey(r)) {
                Double priority = r.getPriority() / (partySkills.get(r).doubleValue() + 1 / party.size()) ;
                ret.put(r, priority);
            } else {
                ret.put(r, 20.0);
            }
        }
        return ret;
    }

    private HashMap<Skill, Integer> getPartySkills(ArrayList<PartyMemberDTO> party) {
        ArrayList<Skill> partySkills = new ArrayList<>();
        for (PartyMemberDTO member : party){
            partySkills.addAll(member.skills);
        }

        HashMap<Skill,Integer> ret = new HashMap<>();
        for(Skill r : partySkills){
            if (ret.containsKey(r)){
                ret.put(r, ret.get(r)+1);
            } else{
                ret.put(r,1);
            }
        }
        return ret;
    }

    public HashMap<Subclass, Double> getCandidatesSkills(HashMap<Skill, Double> neededSkills){
        HashMap<Subclass, Double> candidateSubclasses = getSubclassCandidates(neededSkills);
        List<Map.Entry<Subclass, Double>> highestPriorityList = getHighestPriorityList(candidateSubclasses, 5);
        return convertToMap(highestPriorityList);
    }

    private HashMap<Subclass, Double> getSubclassCandidates(HashMap<Skill, Double> neededSkills) {
        HashMap<Subclass, Double> candidateSubclasses = new HashMap<>();
        for (Skill r : neededSkills.keySet()){
            for(CharClassModel pmm : data){
                if (parseSkillsString(pmm.proficiencies).contains(r)){
                    CharClass charClass = CharClass.valueOf(pmm.charClass.split("\\.")[1]);
                    ArrayList<Subclass> subclasses = new ArrayList<>();
                    for (Subclass s : Subclass.values()){
                        if (s.getCharClass()==charClass) subclasses.add(s);
                    }
                    for (Subclass s : subclasses) {
                        if (candidateSubclasses.containsKey(s))
                            candidateSubclasses.put(s,candidateSubclasses.get(s)+neededSkills.get(r));
                        else
                            candidateSubclasses.put(s,neededSkills.get(r));
                    }
                }
            }
        }
        return candidateSubclasses;
    }

    private static List<Map.Entry<Subclass, Double>> getHighestPriorityList(HashMap<Subclass, Double> ret, Integer n) {
        List<Map.Entry<Subclass, Double>> entryList = new ArrayList<>(ret.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Subclass, Double>>() {
            @Override
            public int compare(Map.Entry<Subclass, Double> entry1, Map.Entry<Subclass, Double> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        List<Map.Entry<Subclass, Double>> highestPriorityEntries = entryList.subList(0, Math.min(n, entryList.size()));
        return highestPriorityEntries;
    }

    private static HashMap<Subclass, Double> convertToMap(List<Map.Entry<Subclass, Double>> highestPriorityEntries) {
        HashMap<Subclass, Double> highestPriority = new HashMap<>();
        for (Map.Entry<Subclass, Double> entry : highestPriorityEntries) {
            Subclass key = entry.getKey();
            double priority = entry.getValue();
            highestPriority.put(key, priority);
        }
        return highestPriority;
    }

    private ArrayList<Skill> parseSkillsString(String proficiencies) {
        ArrayList<Skill> ret = new ArrayList<>();
        for(String s : proficiencies.split(", ")){
            Skill role = Skill.valueOf(s);
            ret.add(role);
        }
        return ret;
    }
}
