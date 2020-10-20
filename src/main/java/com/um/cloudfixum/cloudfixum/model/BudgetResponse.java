package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode

public class BudgetResponse implements Serializable {

    private Long price;

    @NotEmpty(message = Constant.PROVIDER_RESPONSE_NEEDED)
    private String providerResponse;

    private Long budgetId;
}
