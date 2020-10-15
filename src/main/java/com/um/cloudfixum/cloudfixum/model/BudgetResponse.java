package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class BudgetResponse implements Serializable {

    private Long price;

    private String providerResponse;

    private Long budgetId;
}
