package com.datanese.wuye;

public enum ErrorCode {
    /**
     * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期）.
     */
    SESSION_EXPIRED(40014,"Session过期"),
    USER_NOT_EXIST(40015,"用户不存在"),
    BAD_WORD(40066,"评论中含有敏感词");





    private ErrorCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }
    private int code;//code
    private String desc;//描述

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
