package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BudgetStatus {

    BUDGET_ON_HOLD("Budget On Hold"),
    RESPONSED_BUDGET("Responsed Budget"), //Request Budget -anterior.ver
    BUDGET_ACCEPTED("Accepted"),
    BUDGET_REJECTED("Rejected");

    private String status;

    BudgetStatus(String status) {
        this.status = status;
    }

}
