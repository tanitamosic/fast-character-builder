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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SkillService {
    public void getPartyMemberSkillsCSV(){
        InputStream template = SkillService.class.getResourceAsStream("/class/party_skills.drt");

        ArrayList<CharClassModel> data = new ArrayList<>();
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

    public void getPartyMemberSkillsObjects(){
        InputStream template = SkillService.class.getResourceAsStream("/class/party_skills.drt");

//
//        data.add(new PartyMemberModel("wizard", new ArrayList<Skill>(Arrays.asList(Skill.ARCANA,
//                Skill.HISTORY, Skill.INSIGHT, Skill.INVESTIGATION, Skill.MEDICINE, Skill.RELIGION))));
//        data.add(new PartyMemberModel("artificer", new ArrayList<Skill>(Arrays.asList(Skill.ARCANA,
//                Skill.HISTORY, Skill.PERCEPTION, Skill.INVESTIGATION, Skill.MEDICINE, Skill.NATURE, Skill.SLEIGH_OF_HAND))));
//        data.add(new PartyMemberModel("barbarian", new ArrayList<Skill>(Arrays.asList(Skill.ANIMAL_HANDLING,
//                Skill.ATHLETICS, Skill.INTIMIDATION, Skill.NATURE, Skill.PERCEPTION, Skill.SURVIVAL))));
//
//
//        ObjectDataCompiler converter = new ObjectDataCompiler();
//        String drl = converter.compile(data, template);
//
//
//        KieSession ksession = createKieSessionFromDRL(drl);

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
}
