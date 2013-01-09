package com.connyay.confess.gson;

public class Result {
    private Boolean success;
    private String message;
    private ResultData data;

    public Result() {
    }

    public Result(String stuff) {
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

    public void setData(ResultData data) {
	this.data = data;
    }

    public ResultData getData() {
	return data;
    }
}
