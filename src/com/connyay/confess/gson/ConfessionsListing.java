package com.connyay.confess.gson;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfessionsListing {

    private String id;
    private String link;
    private String confession;
    private String date;
    private String status;
    private Votes[] votes;
    private Comments[] comments;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getLink() {
	return link;
    }

    public void setLink(String link) {
	this.link = link;
    }

    public String getConfession() {
	return confession;
    }

    public void setConfession(String confession) {
	this.confession = confession;
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

    public Votes[] getVotes() {
	return votes;
    }

    public void setVotes(Votes[] votes) {
	this.votes = votes;
    }

    public Comments[] getComments() {
	return comments;
    }

    public void setComments(Comments[] comments) {
	this.comments = comments;
    }
    /*
     * // Parcelable interface // We are using write/read value for non
     * primitives to support nulls
     * 
     * public int describeContents() { return 0; }
     * 
     * public void writeToParcel(Parcel out, int flags) { out.writeValue(id);
     * out.writeValue(link); out.writeValue(confession); out.writeValue(date);
     * out.writeValue(status);
     * 
     * }
     * 
     * private ConfessionsListing(Parcel in) { id = (String) in.readValue(null);
     * link = (String) in.readValue(null); confession = (String)
     * in.readValue(null); date = (String) in.readValue(null); status = (String)
     * in.readValue(null);
     * 
     * 
     * }
     * 
     * public static final Parcelable.Creator<ConfessionsListing> CREATOR = new
     * Parcelable.Creator<ConfessionsListing>() { public ConfessionsListing
     * createFromParcel(Parcel in) { return new ConfessionsListing(in); }
     * 
     * public ConfessionsListing[] newArray(int size) { return new
     * ConfessionsListing[size]; } };
     */

}
