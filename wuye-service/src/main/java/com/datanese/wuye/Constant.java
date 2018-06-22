package com.datanese.wuye;


public class Constant {

  public static final int RESPONSE_RESULT_OK = 1;
  public static final int RESPONSE_RESULT_FAIL = 0;



  public static final class ErrorCode1 {
    /**
     * 40001 获取access_token时AppSecret错误，或者access_token无效.
     */
    public static final int ERR_40001 = 40001;

    /**
     * 42001 access_token超时.
     */
    public static final int ERR_42001 = 42001;

    /**
     * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期）.
     */
    public static final int ERR_40014 = 40014;
  }
}
