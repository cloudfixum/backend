package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BudgetStatus {

    RESPONSEDBUDGET("Responsed Budget"), //Request Budget -anterior.ver
    BUDGETONHOLD("Budget On Hold"),
    BUDGETACCEPTED("Accepted"),
    BUDGETREJECTED("Rejected");

    private String status;

    BudgetStatus(String status) {
        this.status = status;
    }

}
