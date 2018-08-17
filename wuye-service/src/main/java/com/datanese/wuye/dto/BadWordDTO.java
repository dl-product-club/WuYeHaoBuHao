package com.datanese.wuye.dto;

public class BadWordDTO {

    private String legalContent;
    private boolean legal;
    public void setLegalContent(String legalContent) {
        this.legalContent = legalContent;
    }
    public String getLegalContent() {
        return legalContent;
    }
    public void setLegal(boolean legal) {
        this.legal = legal;
    }
    public boolean getLegal() {
        return legal;
    }
}
