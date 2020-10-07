package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum BudgetStatus {

    REQUESTEDBUDGET("Request Budget"),
    BUDGETONHOLD("Budget On Hold"),
    BUDGETACCEPTED("Budget Accepted"),
    REJECTEDBUDGET("Rejected Budget");

    private String status;

    BudgetStatus(String status) {
        this.status = status;
    }

}
