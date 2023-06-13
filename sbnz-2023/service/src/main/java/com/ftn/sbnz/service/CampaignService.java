package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import com.ftn.sbnz.model.Subclass;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class CampaignService {

    private KieSession kieSession;
    private List<CampaignModel> data;


    public CampaignService() throws IOException {
        File file = new File("kjar/src/main/resources/class/campaign.drt");
        InputStream template = new FileInputStream(file);
        data = new ArrayList<CampaignModel>();

        data.add(new CampaignModel(Subclass.ALCHEMIST, Amount.LOW.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ARTILLERIST, Amount.LOW.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BEAST, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BERSERKER, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.TOTEM_WARRIOR, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ZEALOT, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.LORE, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.VALOR, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.GLAMOUR, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.LIFE, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.TEMPEST, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.WAR, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.KNOWLEDGE, Amount.MID.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.MOON, Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.LAND, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DREAMS, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.CHAMPION, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BATTLE_MASTER, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ELDRICH_KNIGHT, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.DRUNKEN_MASTER, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.KENSEI, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.OPEN_HAND, Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ANCIENTS, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.DEVOTION, Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.HUNTER, Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.GLOOM, Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.FULL.getEnumString()));
        data.add(new CampaignModel(Subclass.HORIZON, Amount.LOW.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ASSASSIN, Amount.NONE.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ARCANE_TRICKSTER, Amount.MID.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.SWASHBUCKLER, Amount.LOW.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.THIEF, Amount.NONE.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DRACONIC, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DIVINE_SOUL, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.SHADOW, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.WILD, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ARCHFEY, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.FIEND, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.GREAT_OLD_ONE, Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.EVOCATION, Amount.HIGH.getEnumString(), Amount.HIGH.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ABJURATION, Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.CONJURATION, Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DIVINATION, Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.WAR_MAGIC, Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.NECROMANCY, Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.FULL.getEnumString()));
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);

        kieSession = createKieSessionFromDRL(drl);
    }

    public HashMap<Subclass, Double> filterCandidates(HashMap<Subclass, Double> candidates, CampaignDTO campaign) {
        kieSession.insert(candidates);
        kieSession.insert(campaign);
        kieSession.fireAllRules();
        System.out.println("\nfiltered");
        for(Subclass c : candidates.keySet()){
            System.out.println(c.getDisplayName());
        }
        return candidates;
    }

    private KieSession createKieSessionFromDRL(String drl) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)) {
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: " + message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}
