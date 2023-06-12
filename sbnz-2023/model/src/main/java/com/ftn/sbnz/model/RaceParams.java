package com.ftn.sbnz.model;


import java.util.ArrayList;
import java.util.List;

public class RaceParams {
    public Size size;
    public Disposition disposition;
    public Skintone skintone;
    public Feature feature;


    public ColorPallette pallette;
    public Build build;
    public Integer strangeIndex;

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Disposition getDisposition() {
        return disposition;
    }

    public void setDisposition(Disposition disposition) {
        this.disposition = disposition;
    }

    public Skintone getSkintone() {
        return skintone;
    }

    public void setSkintone(Skintone skintone) {
        this.skintone = skintone;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }


    public ColorPallette getPallette() {
        return pallette;
    }

    public void setPallette(ColorPallette pallette) {
        this.pallette = pallette;
    }

    public Build getBuild() {
        return build;
    }

    public void setBuild(Build build) {
        this.build = build;
    }

    public Integer getStrangeIndex() {
        return strangeIndex;
    }

    public void setStrangeIndex(Integer strangeIndex) {
        this.strangeIndex = strangeIndex;
    }
}
