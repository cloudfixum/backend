package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
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
public class Budget implements Serializable, Identificable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = Constant.PROVIDE_DESCRIPTION)
    @Size(min = 10, max = 256, message = Constant.DESCRIPTION_CHARACTERS_LONG)
    private String description;

    @OneToOne
    private ProviderUser requestUser;

    @OneToOne
    private ProviderUser providerUser;



}
