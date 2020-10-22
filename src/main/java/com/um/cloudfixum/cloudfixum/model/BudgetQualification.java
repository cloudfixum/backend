package com.um.cloudfixum.cloudfixum.model;

import com.um.cloudfixum.cloudfixum.common.Constant;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode

public class BudgetQualification implements Serializable {

    private Long budgetId;

    @Range(min=1, max = 5, message = Constant.BUDGET_QUALIFICATION)
    private Integer budgetQualification;
}
