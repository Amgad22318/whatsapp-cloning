package com.example.session5.Models_data;

public class Model_data_chat {
    private String chat_key = null;

    public String getChat_key() {
        return chat_key;
    }

    public void setChat_key(String chat_key) {
        this.chat_key = chat_key;
    }

    public Model_data_chat() {
    }

    private String receiver_phoneNo = null;
    private String sender_phoneNo = null;
    private String receiver_id = null;
    private String user_photo = null;
    private int Chat_seenIndicator = 0;
    private String user_name = null;
    private String user_msg = null;
    private String user_msg_time = null;


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

    public int getChat_seenIndicator() {
        return Chat_seenIndicator;
    }

    public void setChat_seenIndicator(int chat_seenIndicator) {
        Chat_seenIndicator = chat_seenIndicator;
    }


    public String getUser_msg_time() {
        return user_msg_time;
    }

    public String getSender_phoneNo() {
        return sender_phoneNo;
    }

    public void setSender_phoneNo(String sender_phoneNo) {
        this.sender_phoneNo = sender_phoneNo;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public void setUser_msg_time(String user_msg_time) {
        this.user_msg_time = user_msg_time;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_msg() {
        return user_msg;
    }

    public void setUser_msg(String user_msg) {
        this.user_msg = user_msg;
    }
}
