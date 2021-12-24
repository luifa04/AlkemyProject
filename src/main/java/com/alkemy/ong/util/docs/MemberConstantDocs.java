package com.alkemy.ong.util.docs;

public interface MemberConstantDocs {
	
	    String MEMBER = "Member documentation";

	    String MEMBER_DTO_ID = "Member id";
	    String MEMBER_DTO_NAME = "Members's name";
	    String MEMBER_DTO_DESCRIPTION = "Member´s description";
	    String MEMBER_DTO_IMAGE = "Member´s image";
	    String MEMBER_DTO_DATE_CREATION = "Member's date creation";
	    String MEMBER_DTO_DATE_UPDATE = "Member´s date update";

	    String MEMBER_MEMBERREQUESTUPDATE_NAME = "Enter a name for the member";
	    String MEMBER_MEMBERREQUESTUPDATE_DESCRIPTION = "Enter the description of the member";
	    String MEMBER_MEMBERREQUESTUPDATE_IMAGE = "Enter a valid url of an image";

	   /* String CATEGORY_FIND_BY_ID = "Returns a category through id parameter";
	    String CATEGORY_GET_PARAM_ID = "Enter an existing id to search category";
	    */
	    
	    String MEMBER_FIND_ALL = "Returns a paginated list of members";
	    String MEMBER_GET_200_OK = "Member found";
	    String MEMBER_GET_404_NOT_FOUND = "Page number not found";
	    String MEMBER_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search member";
	    
	    
	    String MEMBER_CREATE = "Create new member";
	    String MEMBER_CREATED = "The member has been created";
	    String MEMBER_CREATED_PARAM_MEMBER_REQUEST = "Fill in the required fields to save a member";

	    String MEMBER_UPDATE = "Update an existing member";
	    String MEMBER_UPDATE_OK = "The member has been updated successfully";
	    String MEMBER_UPDATE_PARAM_ID = "Enter an existing id to update member";
	    String MEMBER_UPDATE_PARAM_MEMBER_REQUEST = "Fill in the required fields to update a member";


	    String MEMBER_404_NOT_FOUND = "Cannot find any member";


}
