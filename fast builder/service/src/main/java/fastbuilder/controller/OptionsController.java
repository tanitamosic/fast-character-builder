package fastbuilder.controller;

import fastbuilder.app.model.background.BackgroundParams;
import fastbuilder.app.model.background.Interest;
import fastbuilder.services.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/")
public class OptionsController {

    @Autowired
    BackgroundService backgroundService;

    @GetMapping(value="get")
    public ResponseEntity<String>  getNextOptions() {
        System.out.println("ok");
        List<Interest> interests = backgroundService.getOptions();
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

}

