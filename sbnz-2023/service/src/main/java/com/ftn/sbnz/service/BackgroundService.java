package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class BackgroundService {

    @Autowired
    KieContainer kieContainer;

    public void temp() {
        BackgroundParams params = new BackgroundParams();
        params.wayOfLife = WayOfLife.ONE_PLACE;
        CharSheet charSheet = new CharSheet();
        List<Interest> interests = new ArrayList<>();
//        List</>
        KieSession kieSession = kieContainer.newKieSession("backgroundKSession");
        kieSession.insert(params);
        kieSession.insert(charSheet);
        kieSession.insert(interests);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
