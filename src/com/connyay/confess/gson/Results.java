package com.connyay.confess.gson;

public class Results {
    private Boolean success;
    private String message;
    private ResultsData data;

    public Results() {
    }

    public Results(String stuff) {
	success = null;
	data = null;
	message = null;
    }

    public void setSuccess(Boolean success) {
	this.success = success;
    }

    public Boolean getSuccess() {
	return success;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setData(ResultsData data) {
	this.data = data;
    }

    public ResultsData getData() {
	return data;
    }
}
