package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import com.ftn.sbnz.model.Subclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CampaignService {

    private KieSession kieSession;
    private List<CampaignModel> data;


    public CampaignService() throws IOException {
        File file = new File("kjar/src/main/resources/class/campaign.drt");
        InputStream template = new FileInputStream(file);
        data = new ArrayList<CampaignModel>();

        data.add(new CampaignModel(Subclass.ALCHEMIST.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ARTILLERIST.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BEAST.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BERSERKER.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.TOTEM_WARRIOR.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ZEALOT.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.LORE.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.VALOR.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.GLAMOUR.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.LIFE.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.TEMPEST.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.WAR.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.KNOWLEDGE.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.MOON.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.LAND.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DREAMS.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.CHAMPION.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.BATTLE_MASTER.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ELDRICH_KNIGHT.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.DRUNKEN_MASTER.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.KENSEI.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.OPEN_HAND.getEnumString(), Amount.NONE.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ANCIENTS.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.DEVOTION.getEnumString(), Amount.MID.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString()));
        data.add(new CampaignModel(Subclass.HUNTER.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.GLOOM.getEnumString(), Amount.LOW.getEnumString(), Amount.LOW.getEnumString(), Amount.FULL.getEnumString()));
        data.add(new CampaignModel(Subclass.HORIZON.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ASSASSIN.getEnumString(), Amount.NONE.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ARCANE_TRICKSTER.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.SWASHBUCKLER.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.THIEF.getEnumString(), Amount.NONE.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DRACONIC.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DIVINE_SOUL.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.SHADOW.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.WILD.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.ARCHFEY.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.FIEND.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.GREAT_OLD_ONE.getEnumString(), Amount.HIGH.getEnumString(), Amount.LOW.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.EVOCATION.getEnumString(), Amount.HIGH.getEnumString(), Amount.HIGH.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.ABJURATION.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.CONJURATION.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.DIVINATION.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.MID.getEnumString()));
        data.add(new CampaignModel(Subclass.WAR_MAGIC.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.HIGH.getEnumString()));
        data.add(new CampaignModel(Subclass.NECROMANCY.getEnumString(), Amount.HIGH.getEnumString(), Amount.MID.getEnumString(), Amount.FULL.getEnumString()));

        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);

        kieSession = createKieSessionFromDRL(drl);
        kieSession.setGlobal("candidates", new ArrayList<Subclass>());
    }

    public ArrayList<Subclass> filterCandidates(ArrayList<PartyMemberDTO> party) {
        for (PartyMemberDTO member : party){
            kieSession.insert(member);
        }
        kieSession.fireAllRules();
        ArrayList<Subclass> candidates = (ArrayList<Subclass>) kieSession.getGlobal("candidates");

        for(Subclass c : candidates){
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
