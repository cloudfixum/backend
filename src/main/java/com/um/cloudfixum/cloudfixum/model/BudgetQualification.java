package com.um.cloudfixum.cloudfixum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode

public class BudgetQualification implements Serializable {

    private Long budgetId;

    private Integer budgetQualification;
}
