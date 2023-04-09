package com.example.testtask.constant;

public class Constants {

    public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String DEFAULT_USER_NAME = "UserName";
    public static final String DEFAULT_ARTICLE_TEXT = "Text";
    public static final String USER_NOT_FOUND_BY_ID_MESSAGE = "User with id=%d not found in database";
    public static final String USER_NOT_FOUND_BY_NAME_MESSAGE = "User with name=%s not found in database";

    private Constants() {
        throw new IllegalStateException("Instantiation of this class is not supported");
    }
}