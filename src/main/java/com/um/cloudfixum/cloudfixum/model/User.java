package com.um.cloudfixum.cloudfixum.model;


import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class User implements Serializable, Identificable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username needed")
    @Size(min = 5, max = 40, message = "Username must be between 5 and 40 characters long")
    private String username;

    @NotEmpty(message = "Password needed")
    @Size(min = 5, max = 40, message = "Password requires between 5 and 40 characters long")
    private String password;

    @NotEmpty(message = "Email needed")
    private String email;

}
