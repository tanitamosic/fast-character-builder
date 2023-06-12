package com.ftn.sbnz.model;

public enum Amount {
    NONE,
    LOW,
    MID,
    HIGH,
    FULL;

    public String getEnumString(){
        return "Amount." + this.toString();
    }
}
