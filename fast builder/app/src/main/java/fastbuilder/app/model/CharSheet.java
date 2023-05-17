package fastbuilder.app.model;

import fastbuilder.app.model.enums.Background;
import fastbuilder.app.model.enums.Race;
import fastbuilder.app.model.enums.Subclass;
import fastbuilder.app.model.race.Feature;

public class CharSheet {
    Background background;
    Race race;
    Subclass subclass;
    public Background getBackground() {
        return background;
    }
    public void setBackground(Background background) {
        this.background = background;
    }
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }
    public Subclass getSubclass() {
        return subclass;
    }
    public void setSubclass(Subclass subclass) {
        this.subclass = subclass;
    }
    
    public CharSheet() {
    }
    public CharSheet(Background background, Race race, Subclass subclass) {
        this.background = background;
        this.race = race;
        this.subclass = subclass;
    }

    

    
}
