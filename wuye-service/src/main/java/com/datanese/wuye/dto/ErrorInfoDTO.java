package com.datanese.wuye.dto;

import lombok.Data;

@Data
public class ErrorInfoDTO{
    public static final Integer OK = 0;
    public static final Integer ERROR = 1;

    private int result;
    private int errorCode;
    private String message;
    private String url;
}