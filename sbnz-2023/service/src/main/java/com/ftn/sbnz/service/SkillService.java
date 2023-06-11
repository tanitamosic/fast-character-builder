package com.ftn.sbnz.service;

import com.ftn.sbnz.model.PartyMemberModel;
import com.ftn.sbnz.model.Skill;
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
import java.util.Arrays;
import java.util.List;

@Service
public class SkillService {
    public void getPartyMemberSkillsCSV(){
        InputStream template = SkillService.class.getResourceAsStream("/class/party_skills.drt");
        InputStream data = SkillService.class.getResourceAsStream("//class-skills.csv");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template,1,1);

        KieSession ksession = this.createKieSessionFromDRL(drl);
    }

    public void getPartyMemberSkillsObjects(){
        InputStream template = SkillService.class.getResourceAsStream("/class/party_skills.drt");

        List<PartyMemberModel> data = new ArrayList<PartyMemberModel>();

        data.add(new PartyMemberModel("wizard", new ArrayList<Skill>(Arrays.asList(Skill.ARCANA,
                Skill.HISTORY, Skill.INSIGHT, Skill.INVESTIGATION, Skill.MEDICINE, Skill.RELIGION))));
        data.add(new PartyMemberModel("artificer", new ArrayList<Skill>(Arrays.asList(Skill.ARCANA,
                Skill.HISTORY, Skill.PERCEPTION, Skill.INVESTIGATION, Skill.MEDICINE, Skill.NATURE, Skill.SLEIGH_OF_HAND))));
        data.add(new PartyMemberModel("barbarian", new ArrayList<Skill>(Arrays.asList(Skill.ANIMAL_HANDLING,
                Skill.ATHLETICS, Skill.INTIMIDATION, Skill.NATURE, Skill.PERCEPTION, Skill.SURVIVAL))));


        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);


        KieSession ksession = createKieSessionFromDRL(drl);

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
}
