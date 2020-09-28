package com.um.cloudfixum.cloudfixum.model;
import com.um.cloudfixum.cloudfixum.common.Constant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum Category {

    BARBER("Barber", "https://i.imgur.com/uYyLUkz.jpg", Constant.SUPERCATEGORY_BEAUTY),
    DRESSMAKER("Dressmaker", "https://i.imgur.com/Rm6ivIa.jpg", Constant.SUPERCATEGORY_BEAUTY),
    MAKEUPARTIST("Make-up Artist", "https://i.imgur.com/yA3xEl5.jpg", Constant.SUPERCATEGORY_BEAUTY),
    MANICURE("Manicure", "https://i.imgur.com/f700kTH.jpg", Constant.SUPERCATEGORY_BEAUTY),
    STYLIST("Stylist", "https://i.imgur.com/y2N3mID.jpg", Constant.SUPERCATEGORY_BEAUTY),
    TATTOARTIST("Tatto Artist", "https://i.imgur.com/Kcj2cpg.jpg", Constant.SUPERCATEGORY_BEAUTY),

    TYREREPAIRER("Tyre Repairer", "https://i.imgur.com/WeT732x.jpg", Constant.SUPERCATEGORY_VEHICLE),
    CARALARM("Car Alarm", "https://i.imgur.com/j3BIKKS.jpg" , Constant.SUPERCATEGORY_VEHICLE),
    CARPAINTER("Car Painter", "https://i.imgur.com/kWuLjTC.jpg", Constant.SUPERCATEGORY_VEHICLE),
    PLATER("Plater", "https://i.imgur.com/8mnFWHm.jpg", Constant.SUPERCATEGORY_VEHICLE),
    CARWASH("Car Wash", "https://i.imgur.com/UC5yqZv.jpg", Constant.SUPERCATEGORY_VEHICLE),
    CARELECTRICIAN("Car Electrician", "https://i.imgur.com/7pXyaHo.jpg", Constant.SUPERCATEGORY_VEHICLE),
    MECHANIC("Mechanic","https://i.imgur.com/oWvnMxO.jpg", Constant.SUPERCATEGORY_VEHICLE),

    PERSONAL_TRAINER("Personal Trainer", "https://i.imgur.com/OrXfhz9.jpg", Constant.SUPERCATEGORY_WELLNESS),
    YOGA("Yoga", "https://i.imgur.com/oP95hyn.jpg", Constant.SUPERCATEGORY_WELLNESS),
    MASSAGETHERAPIST("Massage Therapist", "https://i.imgur.com/VJ0rx6Q.jpg", Constant.SUPERCATEGORY_WELLNESS),
    GYM("Gym", "https://i.imgur.com/LktoXq3.jpg", Constant.SUPERCATEGORY_WELLNESS),
    AROMATHERAPY("Aromatherapy","https://i.imgur.com/BIkvpL9.jpg",Constant.SUPERCATEGORY_WELLNESS),

    NURSE("Nurse", "https://i.imgur.com/LRC5bwW.jpg",Constant.SUPERCATEGORY_HEALTH),
    NUTRITIONIST("Nutritionist","https://i.imgur.com/DOBwVh8.jpg", Constant.SUPERCATEGORY_HEALTH),
    PHYCHOLOGIST("Phychologist","https://i.imgur.com/hx1GAFW.jpg",Constant.SUPERCATEGORY_HEALTH),
    DERMATOLOGIST("Dermatologist","https://i.imgur.com/qV10Viu.jpg",Constant.SUPERCATEGORY_HEALTH),
    PEDIATRIST("Pediatrist","https://i.imgur.com/NwqANMe.jpg",Constant.SUPERCATEGORY_HEALTH),
    DENTIST("Dentist","https://i.imgur.com/nH0ii27.jpg",Constant.SUPERCATEGORY_HEALTH),
    CAREGIVER("Caregiver","https://i.imgur.com/HYbYfmq.jpg", Constant.SUPERCATEGORY_HEALTH),

    ELECT_TECH("Electrical Technician", "https://i.imgur.com/uo3yzS5.jpg", Constant.SUPERCATEGORY_HOME),
    GAS_OPERATOR("Gas Operator", "https://i.imgur.com/mDl82vf.jpg", Constant.SUPERCATEGORY_HOME),
    PLUMBER("Plumber", "https://i.imgur.com/J7Aa8y0.jpg", Constant.SUPERCATEGORY_HOME),
    METALLURGICAL("Metallurgical", "https://i.imgur.com/C4zWaX5.jpg", Constant.SUPERCATEGORY_HOME),
    BRICKLAYER("Bricklayer", "https://i.imgur.com/oYwkRfX.jpg", Constant.SUPERCATEGORY_HOME),
    GARDENER("Gardener","https://i.imgur.com/lThUejB.jpg", Constant.SUPERCATEGORY_HOME),
    MAID("Maid", "https://i.imgur.com/gOD4Hgk.jpg", Constant.SUPERCATEGORY_HOME),
    REPAIR_TECH("Repair Technician", "https://i.imgur.com/dRQIFqP.jpg", Constant.SUPERCATEGORY_HOME),
    CARPENTER("Carpenter", "https://i.imgur.com/dHjbElY.jpg", Constant.SUPERCATEGORY_HOME),
    GLAZIER("Glazier", "https://i.imgur.com/pbJbWK8.jpg", Constant.SUPERCATEGORY_HOME),
    AIRCON_TECH("Air Conditioning Technician", "https://i.imgur.com/skB4K1i.jpg", Constant.SUPERCATEGORY_HOME),
    PAINTER("Painter", "https://i.imgur.com/xGLvtIZ.jpg", Constant.SUPERCATEGORY_HOME),
    LOCKSMITH("Locksmith","https://i.imgur.com/uwEvBK8.jpg", Constant.SUPERCATEGORY_HOME),
    TECH_SERVICE("Technician Service", "https://i.imgur.com/E12Dt8x.jpg", Constant.SUPERCATEGORY_HOME),

    PRIVATE_TUTORING("Private Tutoring ", "https://i.imgur.com/QqCTxal.jpg", Constant.SUPERCATEGORY_OTHER),
    BLACKSMITH("Blacksmith", "https://i.imgur.com/rTgbZyf.jpg", Constant.SUPERCATEGORY_OTHER),
    COBBLER("Cobbler", "https://i.imgur.com/JDIwRmx.jpg", Constant.SUPERCATEGORY_OTHER),
    OTHER("Other","https://i.imgur.com/8CJYXG0.jpg", Constant.SUPERCATEGORY_OTHER);

    private String name;
    private String image_url;
    private String superCategory;

    Category (String name, String image_url, String superCategory) {
        this.name = name;
        this. image_url = image_url;
        this.superCategory = superCategory;
    }
}
