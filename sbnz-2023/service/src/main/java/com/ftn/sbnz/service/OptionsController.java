package com.ftn.sbnz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OptionsController {

    @Autowired
    BackgroundService backgroundService;

    @GetMapping(value="get-next-background-option")
    public ResponseEntity<String> get() {
        backgroundService.temp();
        System.out.println("ok");
        return ResponseEntity.ok("ok");
    }
}
