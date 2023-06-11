package com.ftn.sbnz.service;

import com.ftn.sbnz.model.PartyMemberModel;
import com.ftn.sbnz.model.Role;
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
public class RoleService {
    public void getPartyMemberRolesCSV(){
        InputStream template = RoleService.class.getResourceAsStream("/class/party_roles.drt");
        InputStream data = RoleService.class.getResourceAsStream("//class-roles.csv");

        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String drl = converter.compile(data, template,1,1);

        KieSession ksession = this.createKieSessionFromDRL(drl);
    }

    public void getPartyMemberRolesObjects(){
        InputStream template = RoleService.class.getResourceAsStream("/class/party_roles.drt");

        List<PartyMemberModel> data = new ArrayList<PartyMemberModel>();

        data.add(new PartyMemberModel("wizard", new ArrayList<Role>(Arrays.asList(Role.BLASTER,
                Role.DEFENDER, Role.LIBRARIAN, Role.STRIKER, Role.SUPPORT, Role.UTILITY))));
        data.add(new PartyMemberModel("artificer", new ArrayList<Role>(Arrays.asList(Role.DEFENDER,
                Role.HEALER, Role.LIBRARIAN, Role.SUPPORT, Role.UTILITY))));
        data.add(new PartyMemberModel("barbarian", new ArrayList<Role>(Arrays.asList(Role.CONTEROLLER,
                Role.DEFENDER, Role.STRIKER))));


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
