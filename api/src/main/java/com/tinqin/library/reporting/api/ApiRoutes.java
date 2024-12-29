package com.tinqin.library.reporting.api;

public class ApiRoutes {

  public static final String API = "/api/v1";
  public static final String RECORD = API + "/records";
  public static final String GET_RECORD = RECORD + "/{recordId}";
  public static final String CLOSE_RECORD = RECORD + "/close/{recordId}/";
  public static final String  EVENT = API + "/events";
  public static final String  GET_EVENT = EVENT +"/{eventId}";

  public static final String BOOK = API + "/books";


}
