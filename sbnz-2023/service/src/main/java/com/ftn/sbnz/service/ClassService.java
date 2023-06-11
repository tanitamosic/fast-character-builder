package com.ftn.sbnz.service;

import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    KieContainer kieContainer;

    @Autowired
    SkillService skillService;
    @Autowired
    RoleService roleService;

    public void templates() {


    }
}
