package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode

public class BudgetRequest implements Serializable {
    @NotEmpty(message = Constant.EMAIL_NEEDED)
    private String userEmail;

    private String imageHash;

    @NotEmpty(message = Constant.PROVIDE_DESCRIPTION)
    private String description;

    @NotEmpty(message = Constant.LOCATION_NEEDED)
    private String location;

    @NotNull
    private Long minorJobId;
}
