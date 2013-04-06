package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Date;

public class Review {
	private User reviewer;
	private ArrayList<String> reviewOn;
	private Date submitTime;
	private String reviewText;
	
	
	
	public Review(User reviewer, ArrayList<String> reviewOn, Date submitTime,
			String reviewText) {
		super();
		this.reviewer = reviewer;
		this.reviewOn = reviewOn;
		this.submitTime = submitTime;
		this.reviewText = reviewText;
	}
	
	public User getReviewer() {
		return reviewer;
	}
	public void setReviewer(User reviewer) {
		this.reviewer = reviewer;
	}
	public String[] getReviewOn() {
		return (String[]) reviewOn.toArray();
	}
	public void addReviewOn(String reviewOn) {
		this.reviewOn.add(reviewOn);
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	
}
