package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;


@Service
public class RoleService {

    private String partyRolesString = "";
    private ArrayList<Role> neededRoles = new ArrayList<Role>();
    private KieSession kieSession;
    private List<PartyMemberModel> data;

    public RoleService() throws IOException {
        File file = new File("kjar/src/main/resources/class/party_roles.drt");
        InputStream template = new FileInputStream(file);

        data = new ArrayList<>();
        data.add(new PartyMemberModel(CharClass.ARTIFICER.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"DEFENDER, HEALER, LIBRARIAN, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.ARTIFICER.getEnumString(), Subclass.ALCHEMIST.getEnumString(),
				"BLASTER, HEALER, LIBRARIAN, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.ARTIFICER.getEnumString(), Subclass.ARTILLERIST.getEnumString(),
				"BLASTER, CONTROLLER, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.BARBARIAN.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"DEFENDER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.BARBARIAN.getEnumString(), Subclass.BEAST.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.BARBARIAN.getEnumString(), Subclass.BERSERKER.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.BARBARIAN.getEnumString(), Subclass.TOTEM_WARRIOR.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.BARBARIAN.getEnumString(), Subclass.ZEALOT.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.BARD.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"CONTROLLER, FACE, HEALER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.BARD.getEnumString(), Subclass.LORE.getEnumString(),
				"CONTROLLER, FACE, HEALER, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.BARD.getEnumString(), Subclass.VALOR.getEnumString(),
				"BLASTER, CONTROLLER, FACE, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.BARD.getEnumString(), Subclass.GLAMOUR.getEnumString(),
				"CONTROLLER, FACE, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.CLERIC.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, DEFENDER, HEALER, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.CLERIC.getEnumString(), Subclass.LIFE.getEnumString(),
				"DEFENDER, HEALER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.CLERIC.getEnumString(), Subclass.TEMPEST.getEnumString(),
				"BLASTER, CONTROLLER, DEFENDER, HEALER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.CLERIC.getEnumString(), Subclass.WAR.getEnumString(),
				"CONTROLLER, DEFENDER, HEALER, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.CLERIC.getEnumString(), Subclass.KNOWLEDGE.getEnumString(),
				"CONTROLLER, HEALER, LIBRARIAN, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.DRUID.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, CONTROLLER, HEALER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.DRUID.getEnumString(), Subclass.MOON.getEnumString(),
				"DEFENDER, SCOUT, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.DRUID.getEnumString(), Subclass.LAND.getEnumString(),
				"CONTROLLER, HEALER, LIBRARIAN, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.DRUID.getEnumString(), Subclass.DREAMS.getEnumString(),
				"HEALER, LIBRARIAN, SCOUT, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.FIGHTER.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"DEFENDER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.FIGHTER.getEnumString(), Subclass.CHAMPION.getEnumString(),
				"CONTROLLER, DEFENDER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.FIGHTER.getEnumString(), Subclass.BATTLE_MASTER.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.FIGHTER.getEnumString(), Subclass.ELDRICH_KNIGHT.getEnumString(),
				"BLASTER, DEFENDER, SCOUT, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.MONK.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.MONK.getEnumString(), Subclass.DRUNKEN_MASTER.getEnumString(),
				"CONTROLLER, DEFENDER, SCOUT, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.MONK.getEnumString(), Subclass.KENSEI.getEnumString(),
				"CONTROLLER, DEFENDER, SCOUT, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.MONK.getEnumString(), Subclass.OPEN_HAND.getEnumString(),
				"CONTROLLER, DEFENDER, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.PALADIN.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, FACE, HEALER, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.PALADIN.getEnumString(), Subclass.ANCIENTS.getEnumString(),
				"DEFENDER, FACE, HEALER, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.PALADIN.getEnumString(), Subclass.DEVOTION.getEnumString(),
				"DEFENDER, FACE, HEALER, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.RANGER.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"DEFENDER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.RANGER.getEnumString(), Subclass.HUNTER.getEnumString(),
				"BLASTER, CONTROLLER, DEFENDER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.RANGER.getEnumString(), Subclass.GLOOM.getEnumString(),
				"BLASTER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.RANGER.getEnumString(), Subclass.HORIZON.getEnumString(),
				"DEFENDER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.ROGUE.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"FACE, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.ROGUE.getEnumString(), Subclass.ASSASSIN.getEnumString(),
				"FACE, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.ROGUE.getEnumString(), Subclass.ARCANE_TRICKSTER.getEnumString(),
				"CONTROLLER, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.ROGUE.getEnumString(), Subclass.SWASHBUCKLER.getEnumString(),
				"CONTROLLER, FACE, SCOUT, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.ROGUE.getEnumString(), Subclass.THIEF.getEnumString(),
				"CONTROLLER, FACE, SCOUT, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.SORCERER.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, CONTROLLER, FACE, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.SORCERER.getEnumString(), Subclass.DRACONIC.getEnumString(),
				"BLASTER, CONTROLLER, FACE, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.SORCERER.getEnumString(), Subclass.DIVINE_SOUL.getEnumString(),
				"BLASTER, CONTROLLER, HEALER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.SORCERER.getEnumString(), Subclass.SHADOW.getEnumString(),
				"BLASTER, CONTROLLER, STRIKER, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.SORCERER.getEnumString(), Subclass.WILD.getEnumString(),
				"BLASTER, CONTROLLER, FACE, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WARLOCK.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, CONTROLLER, FACE, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.WARLOCK.getEnumString(), Subclass.ARCHFEY.getEnumString(),
				"CONTROLLER, FACE, SCOUT, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.WARLOCK.getEnumString(), Subclass.FIEND.getEnumString(),
				"CONTROLLER, STRIKER, "));
        data.add(new PartyMemberModel(CharClass.WARLOCK.getEnumString(), Subclass.GREAT_OLD_ONE.getEnumString(),
				"CONTROLLER, FACE, LIBRARIAN, SCOUT, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.NO_SUBCLASS.getEnumString(),
				"BLASTER, CONTROLLER, LIBRARIAN, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.EVOCATION.getEnumString(),
				"BLASTER, CONTROLLER, DEFENDER, LIBRARIAN, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.ABJURATION.getEnumString(),
				"CONTROLLER, DEFENDER, HEALER, LIBRARIAN, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.CONJURATION.getEnumString(),
				"CONTROLLER, DEFENDER, LIBRARIAN, SCOUT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.DIVINATION.getEnumString(),
				"CONTROLLER, DEFENDER, LIBRARIAN, STRIKER, SUPPORT, UTILITY, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.WAR_MAGIC.getEnumString(),
				"CONTROLLER, LIBRARIAN, STRIKER, SUPPORT, "));
        data.add(new PartyMemberModel(CharClass.WIZARD.getEnumString(), Subclass.NECROMANCY.getEnumString(),
				"BLASTER, CONTROLLER, LIBRARIAN, STRIKER, "));
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
//        System.out.println(drl);

        kieSession = createKieSessionFromDRL(drl);
        kieSession.setGlobal("partyRoles", new StringBuilder());
    }

    public HashMap<Role, Integer> getPartyRoles(ArrayList<PartyMemberDTO> party) {
        partyRolesString = "";
        for (PartyMemberDTO member : party){
            kieSession.insert(member);
        }

        StringBuilder finalPartyRoles = (StringBuilder) kieSession.getGlobal("partyRoles");
        finalPartyRoles.setLength(0);

        kieSession.fireAllRules();

        partyRolesString = finalPartyRoles.toString();
        System.out.println(partyRolesString);
        ArrayList<Role> rolesList = parseRolesString(partyRolesString);

        return roleListToMap(rolesList);
    }

    private ArrayList<Role> parseRolesString(String partyRolesString) {
        ArrayList<Role> ret = new ArrayList<>();
        for(String s : partyRolesString.split(", ")){
            Role role = Role.valueOf(s);
            ret.add(role);
        }
        return ret;
    }

    private HashMap<Role, Integer> roleListToMap(ArrayList<Role> list) {
        HashMap<Role,Integer> ret = new HashMap<>();
        for(Role r : list){
            if (ret.containsKey(r)){
                ret.put(r, ret.get(r)+1);
            } else{
                ret.put(r,1);
            }
        }
        return ret;
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

    public HashMap<Role, Double> getNeededRoles(HashMap<Role, Integer> partyRoles, Integer partySize) {
        HashMap<Role, Double> ret = new HashMap<Role, Double>();
        Role[] allRoles = Role.values();

        for(Role r : allRoles){
            if (partyRoles.containsKey(r)) {
                Double priority = r.getPriority() / (partyRoles.get(r).doubleValue() + 1 / partySize) ;
                ret.put(r, priority);
            } else {
                ret.put(r, 5.0);
            }
        }
        return ret;
    }

    public HashMap<Subclass, Double> getCandidatesRoles(HashMap<Role, Double> neededRoles){
        HashMap<Subclass, Double> candidateSubclasses = getSubclassCandidates(neededRoles);
        List<Map.Entry<Subclass, Double>> highestPriorityList = getHighestPriorityList(candidateSubclasses, 5);
        return convertToMap(highestPriorityList);
    }

    private HashMap<Subclass, Double> getSubclassCandidates(HashMap<Role, Double> neededRoles) {
        HashMap<Subclass, Double> candidateSubclasses = new HashMap<>();
        for (Role r : neededRoles.keySet()){
            for(PartyMemberModel pmm : data){
                if (parseRolesString(pmm.roles).contains(r)){
                    Subclass subclass = Subclass.valueOf(pmm.subclass.split("\\.")[1]);
                    if (candidateSubclasses.containsKey(subclass))
                        candidateSubclasses.put(subclass,candidateSubclasses.get(subclass)+ neededRoles.get(r));
                    else
                        candidateSubclasses.put(subclass, neededRoles.get(r));
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

}
