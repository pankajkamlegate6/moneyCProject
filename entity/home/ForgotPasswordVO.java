package com.moneycontrol.handheld.entity.home;

import java.io.Serializable;

import org.json.JSONObject;

public class ForgotPasswordVO implements Serializable {

    private String response;
    private String emailAddress;
    private String hint;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
