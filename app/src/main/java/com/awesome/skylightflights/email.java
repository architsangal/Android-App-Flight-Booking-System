package com.awesome.skylightflights;

import java.io.Serializable;

public class email implements Serializable {
    private String body;
    private String email;

    public email() {
    }

    public email(String body, String email) {
        this.body = body;
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
