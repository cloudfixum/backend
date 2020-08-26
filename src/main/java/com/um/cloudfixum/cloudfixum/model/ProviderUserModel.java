package com.um.cloudfixum.cloudfixum.model;


import com.sun.tools.javac.util.List;
import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class ProviderUserModel implements Serializable, Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Identification needed")
    @Size(min = 5, max = 8, message = "ID must be between 5 and 8 characters long")
    private String dni;

    @NotEmpty(message = "Service provider name needed")
    @Size(min = 5, max = 40, message = "Service provider name must be between 5 and 40 characters long")
    private String name;

    @NotEmpty(message = "surname needed")
    @Size(min = 5, max = 40, message = "surname must be between 5 and 40 characters long")
    private String surname;

    @NotEmpty(message = "Email needed")
    private String email;

    @NotEmpty(message = "Phone number needed")
    @Size(min = 10, max = 15, message = "Format: +5492604303030")
    private String prone_number;

    @NotEmpty(message = "Address needed")
    @Size(min = 15, max = 25, message = "Location requires between 15 and 25 characters long")
    private String Address;

    @OneToMany(mappedBy = "Service Provider")
    private List<JobService> serviceList;

}
