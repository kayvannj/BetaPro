package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Date;

public class Review {
	private User reviewer;
	private ArrayList<String> reviewOn;
	private Date submitTime;
	private String reviewText;
	private double rating;
	
	public Review() {
		
	}
	public Review(String s) {
		super();
		
	}
	
	public Review(User reviewer, ArrayList<String> reviewOn, Date submitTime,
			String reviewText, double rating) {
		super();
		this.reviewer = reviewer;
		this.reviewOn = reviewOn;
		this.submitTime = submitTime;
		this.reviewText = reviewText;
		this.rating = rating;
	}
	
	public String toString(){
		String output = "reviewer:"+reviewer+";"
		+"#reviewOn:"+reviewOn.toString()+";"
		+"#submitTime:"+submitTime+";"
		+"#reviewText:"+reviewText+";"
		+"#rating:"+rating;
		return output;
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
	public void removeReviewOn(String reviewOn) {
		this.reviewOn.remove(reviewOn);
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
	public double getRating() {
		return rating;
	}
	public void setRating(double rate) {
		this.rating = rate;
	}
	
	
	
}
