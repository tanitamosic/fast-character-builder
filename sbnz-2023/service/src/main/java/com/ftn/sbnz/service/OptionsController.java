package com.ftn.sbnz.service;

import com.ftn.sbnz.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dnd/")
public class OptionsController {

    private static class BackgroundWrapper {
        public CharSheet charSheet;
        public BackgroundParams backgroundParams;
        public List<Object> nextOptions;
    }

    private static class RaceWrapper {
        public CharSheet charSheet;
        public RaceParams raceParams;
        public List<Object> nextOptions;
        public Boolean optionTypePalette;
        public Boolean optionTypeDisposition;
        public Boolean optionTypeSkin;
        public Boolean optionTypeFeature;
        public Boolean optionTypeBuild;
    }

    @Autowired
    BackgroundService backgroundService;

    @PostMapping(value="get-next-background-option")
    public ResponseEntity<BackgroundWrapper> get(@RequestBody BackgroundWrapper dto) {
        CharSheet charSheet = dto.charSheet;
        BackgroundParams backgroundParams = dto.backgroundParams;
        dto.nextOptions = backgroundService.getNextBackgroundParams(charSheet, backgroundParams);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value="get-next-race-option")
    public ResponseEntity<RaceWrapper> get(@RequestBody RaceWrapper dto) {
        CharSheet charSheet = dto.charSheet;
        RaceParams raceParams = dto.raceParams;
        dto.nextOptions = backgroundService.getNextRaceParams(charSheet, raceParams);
        determineOptionType(dto);
        return ResponseEntity.ok(dto);
    }

    private void determineOptionType(RaceWrapper dto) {
        if (dto.nextOptions.isEmpty()) {
            return;
        }
        for (Object e: dto.nextOptions) {
            if (e instanceof Disposition) {
                dto.optionTypeDisposition = true;
                dto.nextOptions = dto.nextOptions.stream().filter((o) -> o instanceof Disposition).collect(Collectors.toList());
                break;
            } else if (e instanceof ColorPallette) {
                dto.optionTypePalette = true;
                dto.nextOptions = dto.nextOptions.stream().filter((o) -> o instanceof ColorPallette).collect(Collectors.toList());
                break;
            } else if (e instanceof Feature) {
                dto.optionTypeFeature = true;
                dto.nextOptions = dto.nextOptions.stream().filter((o) -> o instanceof Feature).collect(Collectors.toList());
                break;
            } else if (e instanceof Skintone) {
                dto.optionTypeSkin = true;
                dto.nextOptions = dto.nextOptions.stream().filter((o) -> o instanceof Skintone).collect(Collectors.toList());
                break;
            } else if (e instanceof Build) {
                dto.optionTypeBuild = true;
                dto.nextOptions = dto.nextOptions.stream().filter((o) -> o instanceof Build).collect(Collectors.toList());
                break;
            }
        }
    }
}
