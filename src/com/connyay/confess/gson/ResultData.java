package com.connyay.confess.gson;

public class ResultData {
    private ConfessionsListing confessions;
    private String totalCount;

    public void setTotalCount(String totalCount) {
	this.totalCount = totalCount;
    }

    public String getTotalCount() {
	return totalCount;
    }

    public void setConfessions(ConfessionsListing confession) {
	this.confessions = confession;
    }

    public ConfessionsListing getConfessions() {
	return confessions;
    }
}
