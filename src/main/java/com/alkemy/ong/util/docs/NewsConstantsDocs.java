package com.alkemy.ong.util.docs;

public interface NewsConstantsDocs {

    String NEWS  = "News Documentation";

    String NEWS_DTOREQUEST_NAME = "New's name";
    String NEWS_DTOREQUEST_CONTENT = "New's content";
    String NEWS_DTOREQUEST_IMAGE = "New's image";
    String NEWS_DTOREQUEST_CATEGORY = "New's category ID";

    String NEWS_DTORESPONSE_ID = "New's ID";
    String NEWS_DTORESPONSE_NAME = "New's name";
    String NEWS_DTORESPONSE_CONTENT = "New's content";
    String NEWS_DTORESPONSE_IMAGE = "New's image";
    String NEWS_DTORESPONSE_CATEGORY = "New's category id";

    String NEWS_FIND_BY_ID = "Returns a new through id parameter";
    String NEWS_GET_200_OK = "New found";
    String NEWS_GET_404_NOT_FOUND = "Page number not found";

    String NEWS_UPDATE_BY_ID = "Update an existing new";
    String NEWS_UPDATE_BY_ID_OK = "The new has been updated successfully";
    String NEWS_UPDATE_PARAM_ID = "Enter an existing id to update a new";
    String NEWS_UPDATE_PARAM_NEWS_REQUEST = "Fill in the required fields to update a new";

    String NEWS_FIND_ALL_NEWS_BY_NAME = "Returns a paginated list of news";
    String NEWS_GET_PARAM_ID = "Enter an existing id to search news";
    String NEWS_GET_PARAM_PAGE_NUMBER = "Enter an existing number page to search news";

    String NEWS_CREATE = "Create a new";
    String NEWS_CREATED = "The new has been created";
    String NEWS_CREATED_PARAM_NEW_REQUEST = "Fill in the required fields to save a new";

    String NEWS_DELETE = "Delete a new";
    String NEWS_DELETE_OK = "The new has been removed";
    String NEWS_DELETE_PARAM_ID = "Enter an existing id to remove new";
    String NEWS_404_NOT_FOUND = "Cannot find any news";

}
