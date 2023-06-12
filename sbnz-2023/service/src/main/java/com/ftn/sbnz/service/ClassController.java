package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dnd/class")
public class ClassController {

    @Autowired
    ClassService classService;

    @PostMapping("/party")
    public ResponseEntity<String> postParty(@RequestBody PartyDTO dto){
        ArrayList<PartyMemberDTO> party = new ArrayList<>();
        party.add(new PartyMemberDTO(CharClass.BARBARIAN, Subclass.NO_SUBCLASS, new ArrayList<>()));
        classService.temp(party);
        return ResponseEntity.ok("sve ok");
    }

}
