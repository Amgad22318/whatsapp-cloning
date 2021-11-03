package com.example.session5.Models_data;

public class Model_data_chat_FAB {
    private String chat_key=null;

    public String getChat_key() {
        return chat_key;
    }

    public void setChat_key(String chat_key) {
        this.chat_key = chat_key;
    }

    private String receiver_about = null;
    private String receiver_phoneNo = null;
    private String receiver_id=null;
    private String sender_phoneNo = null;
    private String user_name = null;
    private String user_photo = null;

    public String getReceiver_about() {
        return receiver_about;
    }

    public void setReceiver_about(String receiver_about) {
        this.receiver_about = receiver_about;
    }


    public Model_data_chat_FAB() {
    }

    public String getReceiver_phoneNo() {
        return receiver_phoneNo;
    }

    public void setReceiver_phoneNo(String receiver_phoneNo) {
        this.receiver_phoneNo = receiver_phoneNo;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getSender_phoneNo() {
        return sender_phoneNo;
    }

    public void setSender_phoneNo(String sender_phoneNo) {
        this.sender_phoneNo = sender_phoneNo;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
}
