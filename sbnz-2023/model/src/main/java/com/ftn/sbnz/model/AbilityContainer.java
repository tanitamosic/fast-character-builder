package com.ftn.sbnz.model;

import org.kie.api.definition.type.Position;

public class AbilityContainer {

    @Position(0)
    public String part;
//    public String charClass;
    @Position(1)
    public String whole;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

//    public String getCharClass() {
//        return charClass;
//    }
//
//    public void setCharClass(String charClass) {
//        this.charClass = charClass;
//    }

    public String getWhole() {
        return whole;
    }

    public void setWhole(String whole) {
        this.whole = whole;
    }

    public AbilityContainer(String part, String whole) {
        this.part = part;
        this.whole = whole;
    }

    public AbilityContainer() {
    }
}
