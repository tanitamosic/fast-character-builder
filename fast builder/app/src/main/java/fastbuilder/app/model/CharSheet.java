package fastbuilder.app.model;

import fastbuilder.app.model.background.Background;
import fastbuilder.app.model.race.Race;
import fastbuilder.app.model.subclass.CharClass;

public class CharSheet {
    Background background;
    Race race;
    CharClass charClass;
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
    public CharClass getSubclass() {
        return charClass;
    }
    public void setSubclass(CharClass charClass) {
        this.charClass = charClass;
    }
    
    public CharSheet() {
    }
    public CharSheet(Background background, Race race, CharClass charClass) {
        this.background = background;
        this.race = race;
        this.charClass = charClass;
    }

    

    
}
