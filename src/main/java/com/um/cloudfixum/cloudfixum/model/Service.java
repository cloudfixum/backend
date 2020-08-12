package com.um.cloudfixum.cloudfixum.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import lombok.*;

@Getter
@Setter
public class Service implements Serializable {
    private static final long serialVersionUID = 3159888465448526457L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_service;
    private String service_name;
    private String image_service;
    private String category;
    private Long base_price;
    private String description;
}
