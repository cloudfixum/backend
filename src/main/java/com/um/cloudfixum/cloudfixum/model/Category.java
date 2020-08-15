package com.um.cloudfixum.cloudfixum.model;
import lombok.Getter;
import lombok.ToString;
@Getter
@ToString
public enum Category {
    BARBER("Barber", ""),
    ELECT_TECH("Electrical Technician", ""),
    GAS_OPERATOR("Gas Operator", ""),
    PLUMBER("Plumber", ""),
    METALLURGICAL("Metallurgical", ""),
    BRICKLAYER("Bricklayer", ""),
    GARDENER("Gardener",""),
    CAREGIVER("Caregiver",""),
    MAID("Maid", ""),
    REPAIR_TECH("Repair Technician", ""),
    MECHANIC("Mechanic",""),
    TECH_SERVICE("Technician Service", ""),
    PRIVATE_TUTORING("Private Tutoring ", ""),
    PERSONAL_TRAINER("Personal Trainer", ""),
    CARPENTER("Carpenter", ""),
    GLAZIER("Glazier", ""),
    BLACKSMITH("Blacksmith", ""),
    COBBLER("Cobbler", ""),
    DRESSMAKER("Dressmaker", ""),
    AIRCON_TECH("Air Conditioning Technician", ""),
    PAINTER("Painter", ""),
    LOCKSMITH("Locksmith",""),
    OTHER("Other","");

    private String name;
    private String image_url;

    Category (String name, String image_url) {
        this.name = name;
        this. image_url = image_url;
    }
}
