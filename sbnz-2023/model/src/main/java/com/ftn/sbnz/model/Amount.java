package com.ftn.sbnz.model;

public enum Amount {
    NONE(1),
    LOW(2),
    MID(3),
    HIGH(4),
    FULL(5);

    private Integer value;

    private Amount(Integer v){
        this.value = v;
    }

    public Integer getDif(Amount that) { return Math.abs(this.value - that.value); }
    public Boolean greaterThan(Amount that) {return this.value > that.value; }
    public Integer getValue() { return this.value; }
    public String getEnumString(){
        return "Amount." + this.toString();
    }
}
