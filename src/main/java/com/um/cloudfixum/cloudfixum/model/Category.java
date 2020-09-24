package com.um.cloudfixum.cloudfixum.model;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {
    BARBER("Barber", "https://i.imgur.com/uYyLUkz.jpg", "Image"),
    ELECT_TECH("Electrical Technician", "https://i.imgur.com/uo3yzS5.jpg", "Home"),
    GAS_OPERATOR("Gas Operator", "https://i.imgur.com/mDl82vf.jpg", "Home"),
    PLUMBER("Plumber", "https://i.imgur.com/J7Aa8y0.jpg", "Home"),
    METALLURGICAL("Metallurgical", "https://i.imgur.com/C4zWaX5.jpg", "Office"),
    BRICKLAYER("Bricklayer", "https://i.imgur.com/oYwkRfX.jpg", "Home"),
    GARDENER("Gardener","https://i.imgur.com/lThUejB.jpg", "Home"),
    CAREGIVER("Caregiver","https://i.imgur.com/HYbYfmq.jpg", "Health"),
    MAID("Maid", "https://i.imgur.com/gOD4Hgk.jpg", "Home"),
    REPAIR_TECH("Repair Technician", "https://i.imgur.com/dRQIFqP.jpg", "Technology"),
    MECHANIC("Mechanic","https://i.imgur.com/oWvnMxO.jpg", "Vehicle"),
    TECH_SERVICE("Technician Service", "https://i.imgur.com/E12Dt8x.jpg", "Technology"),
    PRIVATE_TUTORING("Private Tutoring ", "https://i.imgur.com/QqCTxal.jpg", "Education"),
    PERSONAL_TRAINER("Personal Trainer", "https://i.imgur.com/OrXfhz9.jpg", "Sports"),
    CARPENTER("Carpenter", "https://i.imgur.com/dHjbElY.jpg", "Office"),
    GLAZIER("Glazier", "https://i.imgur.com/pbJbWK8.jpg", "Office"),
    BLACKSMITH("Blacksmith", "https://i.imgur.com/rTgbZyf.jpg", "Office"),
    COBBLER("Cobbler", "https://i.imgur.com/JDIwRmx.jpg", "Fashion"),
    DRESSMAKER("Dressmaker", "https://i.imgur.com/Rm6ivIa.jpg", "Fashion"),
    AIRCON_TECH("Air Conditioning Technician", "https://i.imgur.com/skB4K1i.jpg", "Home"),
    PAINTER("Painter", "https://i.imgur.com/xGLvtIZ.jpg", "Home"),
    LOCKSMITH("Locksmith","https://i.imgur.com/uwEvBK8.jpg", "Office"),
    OTHER("Other","https://i.imgur.com/8CJYXG0.jpg", "Other");

    private String name;
    private String image_url;
    private String superCategory;

    Category (String name, String image_url, String superCategory) {
        this.name = name;
        this. image_url = image_url;
        this.superCategory = superCategory;
    }
}
