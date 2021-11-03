package com.example.session5.Models_data;

public class Model_data_message {

    private String message_text, sender_phoneNo;
    private String message_time;

    public String getSender_phoneNo() {
        return sender_phoneNo;
    }

    public void setSender_phoneNo(String sender_phoneNo) {
        this.sender_phoneNo = sender_phoneNo;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }


    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }
}
