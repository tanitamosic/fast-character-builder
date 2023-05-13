package fastbuilder.app.model;

import fastbuilder.app.model.enums.Background;
import fastbuilder.app.model.enums.Race;
import fastbuilder.app.model.enums.Subclass;
import fastbuilder.kjar.model.race.Feature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharSheet {
    Background background;
    Race race;
    Subclass subclass;
}
