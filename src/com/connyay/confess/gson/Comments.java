package com.connyay.confess.gson;

public class Comments {
    private String text;
    private String date;
    private String status;
    private String id;

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

}
