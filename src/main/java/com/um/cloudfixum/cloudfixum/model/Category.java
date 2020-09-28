package com.um.cloudfixum.cloudfixum.model;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {

    BARBER("Barber", "https://i.imgur.com/uYyLUkz.jpg", "Beauty"),
    DRESSMAKER("Dressmaker", "https://i.imgur.com/Rm6ivIa.jpg", "Beauty"),
    MAKEUPARTIST("Make-up Artist", "https://i.imgur.com/yA3xEl5.jpg", "Beauty"),
    MANICURE("Manicure", "https://i.imgur.com/f700kTH.jpg", "Beauty"),
    STYLIST("Stylist", "https://i.imgur.com/y2N3mID.jpg", "Beauty"),
    TATTOARTIST("Tatto Artist", "https://i.imgur.com/Kcj2cpg.jpg", "Beauty"),

    TYREREPAIRER("Tyre Repairer", "https://i.imgur.com/WeT732x.jpg", "Vehicle"),
    CARALARM("Car Alarm", "https://i.imgur.com/j3BIKKS.jpg" , "Vehicle"),
    CARPAINTER("Car Painter", "https://i.imgur.com/kWuLjTC.jpg", "Vehicle"),
    PLATER("Plater", "https://i.imgur.com/8mnFWHm.jpg", "Vehicle"),
    CARWASH("Car Wash", "https://i.imgur.com/UC5yqZv.jpg", "Vehicle"),
    CARELECTRICIAN("Car Electrician", "https://i.imgur.com/7pXyaHo.jpg", "Vehicle"),
    MECHANIC("Mechanic","https://i.imgur.com/oWvnMxO.jpg", "Vehicle"),

    PERSONAL_TRAINER("Personal Trainer", "https://i.imgur.com/OrXfhz9.jpg", "Wellness"),
    YOGA("Yoga", "https://i.imgur.com/oP95hyn.jpg", "Wellness"),
    MASSAGETHERAPIST("Massage Therapist", "https://i.imgur.com/VJ0rx6Q.jpg", "Wellness"),
    GYM("Gym", "https://i.imgur.com/LktoXq3.jpg", "Wellness"),
    AROMATHERAPY("Aromatherapy","https://i.imgur.com/BIkvpL9.jpg","Wellness"),

    NURSE("Nurse", "https://i.imgur.com/LRC5bwW.jpg","Health"),
    NUTRITIONIST("Nutritionist","https://i.imgur.com/DOBwVh8.jpg", "Health"),
    PHYCHOLOGIST("Phychologist","https://i.imgur.com/hx1GAFW.jpg","Health"),
    DERMATOLOGIST("Dermatologist","https://i.imgur.com/qV10Viu.jpg","Health"),
    PEDIATRIST("Pediatrist","https://i.imgur.com/NwqANMe.jpg","Health"),
    DENTIST("Dentist","https://i.imgur.com/nH0ii27.jpg","Health"),
    CAREGIVER("Caregiver","https://i.imgur.com/HYbYfmq.jpg", "Health"),

    ELECT_TECH("Electrical Technician", "https://i.imgur.com/uo3yzS5.jpg", "Home"),
    GAS_OPERATOR("Gas Operator", "https://i.imgur.com/mDl82vf.jpg", "Home"),
    PLUMBER("Plumber", "https://i.imgur.com/J7Aa8y0.jpg", "Home"),
    METALLURGICAL("Metallurgical", "https://i.imgur.com/C4zWaX5.jpg", "Home"),
    BRICKLAYER("Bricklayer", "https://i.imgur.com/oYwkRfX.jpg", "Home"),
    GARDENER("Gardener","https://i.imgur.com/lThUejB.jpg", "Home"),
    MAID("Maid", "https://i.imgur.com/gOD4Hgk.jpg", "Home"),
    REPAIR_TECH("Repair Technician", "https://i.imgur.com/dRQIFqP.jpg", "Home"),
    CARPENTER("Carpenter", "https://i.imgur.com/dHjbElY.jpg", "Home"),
    GLAZIER("Glazier", "https://i.imgur.com/pbJbWK8.jpg", "Home"),
    AIRCON_TECH("Air Conditioning Technician", "https://i.imgur.com/skB4K1i.jpg", "Home"),
    PAINTER("Painter", "https://i.imgur.com/xGLvtIZ.jpg", "Home"),
    LOCKSMITH("Locksmith","https://i.imgur.com/uwEvBK8.jpg", "Home"),
    TECH_SERVICE("Technician Service", "https://i.imgur.com/E12Dt8x.jpg", "Home"),

    PRIVATE_TUTORING("Private Tutoring ", "https://i.imgur.com/QqCTxal.jpg", "Other"),
    BLACKSMITH("Blacksmith", "https://i.imgur.com/rTgbZyf.jpg", "Other"),
    COBBLER("Cobbler", "https://i.imgur.com/JDIwRmx.jpg", "Other"),
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
