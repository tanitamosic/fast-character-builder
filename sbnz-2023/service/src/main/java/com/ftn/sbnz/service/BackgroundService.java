package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BackgroundService {

    @Autowired
    KieContainer kieContainer;

    public List<Object> getNextBackgroundParams(CharSheet charSheet, BackgroundParams backgroundParams) {
        List<Object> newOptions = new ArrayList<>();
        KieSession kieSession = kieContainer.newKieSession("backgroundKSession");
        kieSession.insert(backgroundParams);
        kieSession.insert(charSheet);
        kieSession.insert(newOptions);
        kieSession.fireAllRules();
        kieSession.dispose();
        return newOptions;
    }

    public List<Object> getNextRaceParams(CharSheet charSheet, RaceParams raceParams) {
        List<Object> newOptions = new ArrayList<>();
        KieSession kieSession = kieContainer.newKieSession("raceKSession");
        kieSession.insert(raceParams);
        kieSession.insert(charSheet);
        kieSession.insert(newOptions);
        kieSession.fireAllRules();
        kieSession.dispose();
        return newOptions;
    }
}
