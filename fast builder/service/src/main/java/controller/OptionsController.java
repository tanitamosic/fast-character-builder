package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class OptionsController {

    @GetMapping(value="get-next-options")
    public <T> ResponseEntity<T>  getNextOptions() {

        return null;
    }

}
