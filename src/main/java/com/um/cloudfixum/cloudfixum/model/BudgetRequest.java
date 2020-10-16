package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter

public class BudgetRequest implements Serializable {
    @NotEmpty(message = Constant.EMAIL_NEEDED)
    private String userEmail;

    private String imageHash;

    @NotEmpty(message = Constant.PROVIDE_DESCRIPTION)
    private String description;

    @NotEmpty(message = Constant.LOCATION_NEEDED)
    private String location;


    private Long minorJobId;
}
