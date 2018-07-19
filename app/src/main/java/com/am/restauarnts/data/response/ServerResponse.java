package com.am.restauarnts.data.response;

public abstract class ServerResponse{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
