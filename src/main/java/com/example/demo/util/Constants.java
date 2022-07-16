package com.example.demo.util;

public class Constants {

  /*
   * Auth api endpoint
   */
  public static final String LOGIN = "/login";
  public static final String LOGOUT = "/logout";

  public static final String API_ENDPOINT = "/api/v1/auth";

  public static final String LOGIN_ENDPOINT = API_ENDPOINT + LOGIN;
  public static final String LOGOUT_ENDPOINT = API_ENDPOINT + LOGOUT;

  public static final String GET_AUTH_INFO = "/get_auth_info";
  public static final String GET_AUTH_INFO_ENDPOINT =
    API_ENDPOINT + GET_AUTH_INFO;

  /*
   * Test api endpoint
   */
  public static final String READ = "/read";
  public static final String WRITE = "/write";
  public static final String MANAGER_ONLY = "/manager_only";

  public static final String TEST_ENDPOINT = "/api/v1/test";

  public static final String MANAGER_ONLY_ENDPOINT =
    TEST_ENDPOINT + MANAGER_ONLY;

  public static final String READ_ENDPOINT = TEST_ENDPOINT + READ;
  public static final String WRITE_ENDPOINT = TEST_ENDPOINT + WRITE;

  /*
   * Addtional constant
   */
  public static final String SWAGGER_UI_ENDPOINT_1 = "/swagger-ui/**";
  public static final String SWAGGER_UI_ENDPOINT_2 = "/v3/api-docs/**";
  public static final String UserSessionKey = "user_in_session_key";
}
