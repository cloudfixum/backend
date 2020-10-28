package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
import com.um.cloudfixum.cloudfixum.common.Identificable;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotEmpty(message = Constant.EMAIL_NEEDED)
    @Email(message = Constant.EMAIL_FORMAT)
    private String userEmail;

    @Size(min = 10, max = 256, message = Constant.RESPONSE_CHARACTERS_LONG)
    private String providerResponse;

    @NotEmpty(message = Constant.LOCATION_NEEDED)
    @Size(min = 4, max = 50, message = Constant.LOCATION_CHARACTERS_LONG)
    private String location;

    private Long budgetPrice;

    @Column(columnDefinition = "TEXT")
    private String imageHash;//BASE64

    @NotNull(message = Constant.MESSAGE_BUDGETSTATUS)
    @Enumerated(EnumType.STRING)
    private BudgetStatus budgetStatus;

    private Integer qualification;

    @ManyToOne
    private MinorJob minorJob;


    public Budget(BudgetRequest budgetRequest){
        this.budgetStatus = BudgetStatus.BUDGET_ON_HOLD;
        this.userEmail = budgetRequest.getUserEmail();
        this.description = budgetRequest.getDescription();
        this.imageHash = budgetRequest.getImageHash();
        this.location = budgetRequest.getLocation();


    }
    
}
