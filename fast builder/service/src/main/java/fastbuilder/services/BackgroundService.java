package fastbuilder.services;


import fastbuilder.app.model.background.BackgroundParams;
import fastbuilder.app.model.background.Interest;
import fastbuilder.app.model.background.WayOfLife;
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

    public List<Interest> getOptions() {
        List<Interest> options = new ArrayList<>();
        BackgroundParams backgroundParams = new BackgroundParams();
        backgroundParams.wayOfLife = WayOfLife.ONE_PLACE;

        KieSession kieSession = kieContainer.newKieSession("backgroundKSession");
        kieSession.insert(backgroundParams);
        kieSession.insert(options);
        kieSession.fireAllRules();
        kieSession.dispose();
        return options;
    }

}
