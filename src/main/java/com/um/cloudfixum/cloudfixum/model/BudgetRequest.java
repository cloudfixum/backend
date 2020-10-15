package com.um.cloudfixum.cloudfixum.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class BudgetRequest implements Serializable {

    private String user_email;

    private String image_url_encoded;

    private String description;

    private String location;

    private Long minor_job;
}
