package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class BudgetRequest implements Serializable {

    private String userEmail;

    private String imageHash;

    private String description;

    private String location;

    private Long minorJobId;
}
