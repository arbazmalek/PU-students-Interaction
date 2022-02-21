package com.arbaz.pusiapp.Models;

public class MessageModel {

    String uId , message , userName;
    Long timestamp;


    public MessageModel(String uId, String message, String userName, Long timestamp) {
        this.uId = uId;
        this.message = message;
        this.userName = userName;
        this.timestamp = timestamp;

    }

    public MessageModel(String uId, String message, String userName) {
        this.uId = uId;
        this.message = message;
        this.userName = userName;
    }

    public MessageModel(){}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
