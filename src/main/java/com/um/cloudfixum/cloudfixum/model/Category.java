package com.um.cloudfixum.cloudfixum.model;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {
    BARBER("Barber", "https://i.imgur.com/uYyLUkz.jpg"),
    ELECT_TECH("Electrical Technician", "https://i.imgur.com/uo3yzS5.jpg"),
    GAS_OPERATOR("Gas Operator", "https://i.imgur.com/mDl82vf.jpg"),
    PLUMBER("Plumber", "https://i.imgur.com/J7Aa8y0.jpg"),
    METALLURGICAL("Metallurgical", "https://i.imgur.com/C4zWaX5.jpg"),
    BRICKLAYER("Bricklayer", "https://i.imgur.com/oYwkRfX.jpg"),
    GARDENER("Gardener","https://i.imgur.com/lThUejB.jpg"),
    CAREGIVER("Caregiver","https://i.imgur.com/HYbYfmq.jpg"),
    MAID("Maid", "https://i.imgur.com/gOD4Hgk.jpg"),
    REPAIR_TECH("Repair Technician", "https://i.imgur.com/dRQIFqP.jpg"),
    MECHANIC("Mechanic","https://i.imgur.com/oWvnMxO.jpg"),
    TECH_SERVICE("Technician Service", "https://i.imgur.com/E12Dt8x.jpg"),
    PRIVATE_TUTORING("Private Tutoring ", "https://i.imgur.com/QqCTxal.jpg"),
    PERSONAL_TRAINER("Personal Trainer", "https://i.imgur.com/OrXfhz9.jpg"),
    CARPENTER("Carpenter", "https://i.imgur.com/dHjbElY.jpg"),
    GLAZIER("Glazier", "https://i.imgur.com/pbJbWK8.jpg"),
    BLACKSMITH("Blacksmith", "https://i.imgur.com/rTgbZyf.jpg"),
    COBBLER("Cobbler", "https://i.imgur.com/JDIwRmx.jpg"),
    DRESSMAKER("Dressmaker", "https://i.imgur.com/Rm6ivIa.jpg"),
    AIRCON_TECH("Air Conditioning Technician", "https://i.imgur.com/skB4K1i.jpg"),
    PAINTER("Painter", "https://i.imgur.com/xGLvtIZ.jpg"),
    LOCKSMITH("Locksmith","https://i.imgur.com/uwEvBK8.jpg"),
    OTHER("Other","https://i.imgur.com/0T0mIYM.png");

    private String name;
    private String image_url;

    Category (String name, String image_url) {
        this.name = name;
        this. image_url = image_url;
    }
}
