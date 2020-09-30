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
    public static final String FORMAT_DATE = "dd-MM-yyyy";

    //Model ProviderUser
    public static final String DNI_NEEDED = "Identification needed";
    public static final String DNI_CHARACTERS = "DNI must be between 5 and 9 characters long";
    public static final String NAME_NEEDED = "Service provider name needed";
    public static final String NAME_CHARACTERS = "Service provider name must be between 3 and 40 characters long";
    public static final String LAST_NAME_NEEDED = "last name needed";
    public static final String LAST_NAME_CHARACTERS = "last name must be between 5 and 40 characters long";
    public static final String EMAIL_NEEDED = "Email needed";
    public static final String EMAIL_FORMAT = "Invalid Email format";
    public static final String PHONE_NUMBER_NEEDED = "Phone number needed";
    public static final String MESSAGE_PHONE_NUMBER = "Format: +5492604303030";
    public static final String ADDRESS_NEEDED = "Address needed";
    public static final String ADDRESS_CHARACTERS = "Address requires between 6 and 40 characters long";
    public static final String LOCATION_NEEDED = "Location needed";
    public static final String LOCATION_CHARACTERS = "Location requires between 6 and 40 characters long";
    public static final String SERVICE_PROVIDER = "serviceProvider";
    public static final String PASSWORD_NEEDED = "Password required";
    public static final String PASSWORD_CHARACTERS = "Password requires a minimum of 8 characters";

    //Enum
    public static final String SUPERCATEGORY_HOME = "Home";
    public static final String SUPERCATEGORY_HEALTH = "Health";
    public static final String SUPERCATEGORY_OTHER = "Other";
    public static final String SUPERCATEGORY_BEAUTY = "Beauty";
    public static final String SUPERCATEGORY_VEHICLE = "Vehicle";
    public static final String SUPERCATEGORY_WELLNESS = "Wellness";

}
