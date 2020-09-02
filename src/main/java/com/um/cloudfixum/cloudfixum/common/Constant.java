package com.um.cloudfixum.cloudfixum.common;

public class Constant {
    // CorsConfiguration
    public static final String BAR_ASTERISK = "/**";
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "HEAD"};
    public static final String[] EXPOSED_HEADERS = {"Prev", "Next", "CurrentPage", "Size", "TotalPages", "TotalRecords"};
    public static final String HTTP_LOCALHOST = "http://localhost:3000";
    public static final String HTTP_DEVELOP_HEROKU_FRONT = "https://cloudfixum-develop.herokuapp.com";
    public static final String HTTP_MASTER_HEROKU_FRONT = "https://cloudfixum.herokuapp.com";
    public static final String ASTERISK = "*";

    //CustomErrorController
    public static final String ERROR = "error";
    public static final String NOT_FOUND = "Not found";

    //CustomGlobalExceptionHandler
    public static final String TIMESTAMP = "timestamp";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";

    //SwaggerConfig
    public static final String API = "/api/**";

    //Model MinorJob
    public static final String TITLE_PROVIDE = "You must provide a title";
    public static final String TITLE_CHARACTERS = "Title must be between 5 and 50 characters long";
    public static final String MESSAGE_CATEGORY = "Category can't be null";
    public static final String PRICE_NOT_NULL = "Price can't be null";
    public static final String PROVIDE_DESCRIPTION = "You must provide a description";
    public static final String DESCRIPTION_CHARACTERS_LONG = "Description can't exceed 256 characters long";
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    //Model ProviderUser
    public static final String DNI_NEEDED = "Identification needed";
    public static final String DNI_CHARACTERS = "ID must be between 5 and 8 characters long";
    public static final String NAME_NEEDED = "Service provider name needed";
    public static final String NAME_CHARACTERS = "Service provider name must be between 3 and 40 characters long";
    public static final String LAST_NAME_NEEDED = "last name needed";
    public static final String LAST_NAME_CHARACTERS = "last name must be between 5 and 40 characters long";
    public static final String EMAIL_NEEDED = "Email needed";
    public static final String PHONE_NUMBER_NEEDED = "Phone number needed";
    public static final String MESSAGE_PHONE_NUMBER = "Format: +5492604303030";
    public static final String ADDRESS_NEEDED = "Address needed";
    public static final String ADDRESS_CHARACTERS = "Location requires between 6 and 40 characters long";
    public static final String SERVICE_PROVIDER = "serviceProvider";

}
