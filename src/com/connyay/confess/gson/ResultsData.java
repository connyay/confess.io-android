package com.connyay.confess.gson;

public class ResultsData {
    private ConfessionsListing[] confessions;
    private String totalCount;

    public void setTotalCount(String totalCount) {
	this.totalCount = totalCount;
    }

    public String getTotalCount() {
	return totalCount;
    }

    public void setConfessions(ConfessionsListing[] confessions) {
	this.confessions = confessions;
    }

    public ConfessionsListing[] getConfessions() {
	return confessions;
    }
}
