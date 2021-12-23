package com.alkemy.ong.controller.docs;

public interface AuthenticationConstantDocs {

	String AUTHENTICATION = "Testimonial Docs";
	
	String AUTHENTICATION_SINGUP = "Register a new user";
	String AUTHENTICATION_SINGUP_NOTE = "This endpoint is used to register a new user, by default user permissions are assigned";

	String AUTHENTICATION_SINGIN = "Login with a registered user";
	String AUTHENTICATION_SINGIN_NOTE = "This endpoint will be used to log users already registered, returns the token";

	String AUTHENTICATION_ME = "Returns user information from token";
	String AUTHENTICATION_ME_NOTE = "This endpoint returns the ID, first name, last name, email and photo of the user from token";

	String AUTHENTICATION_TOKEN = "User's token, contains the user's id, roles, email and the expiration date of the token";
	String AUTHENTICATION_EMAIL = "User email, cannot be empty";
	String AUTHENTICATION_PASS = "User password, cannot be empty";

	String AUTHENTICATION_ID = "Unique user ID";
	String AUTHENTICATION_FIRSTNAME = "User's first name";
	String AUTHENTICATION_LASTNAME = "User's last name";
	String AUTHENTICATION_PHOTO = "User's photo";

	String AUTHENTICATION_USERREQUEST_PASS = "User password, cannot be empty and its minimum length is 5 characters";
	String AUTHENTICATION_USERREQUEST_FIRSTNAME = "User's first name, cannot be empty";
	String AUTHENTICATION_USERREQUEST_LASTNAME = "User's last name, cannot be empty";
	String AUTHENTICATION_USERREQUEST_EMAIL = "User's email, cannot be empty, It must also have the email format example@example.com";
	String AUTHENTICATION_USERREQUEST_PHOTO = "User's photo, can be null";

	String AUTHENTICATION_SINGUP_PARAM = "User information for registration, sent through the body";
	String AUTHENTICATION_SINGIN_PARAM = "User information for the login, sent through the body";
	String AUTHENTICATION_ME_PARAM = "User token sent in the header";


}
