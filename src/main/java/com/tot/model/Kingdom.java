package com.tot.model;

public enum Kingdom {
    LAND("PANDA"),
    WATER("OCTOPUS"),
    ICE("MAMMOTH"),
    AIR("OWL"),
    FIRE("DRAGON"),
    SPACE("GORILLA");

    private final String emblem;

    Kingdom(String emblem) {
        this.emblem = emblem;
    }

    public String getEmblem() {
        return emblem;
    }

    public static Kingdom getenumObject(String emblem) throws IllegalArgumentException {
        if(emblem!=null) {
            for(Kingdom t:Kingdom.values()) {
                if(t.getEmblem().equals(emblem)) {
                    return t;
                }
            }
        }
        else {
            throw new IllegalArgumentException();
        }

        return null;

    }

}
