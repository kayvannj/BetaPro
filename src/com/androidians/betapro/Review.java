package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Date;

import android.content.SharedPreferences;

public class Review {
	private String reviewer;
	private String reviewID;
	private ArrayList<String> reviewOn;
	private Date submitTime;
	private String reviewText;
	private double rating;
	
	public Review() {
		reviewOn = new ArrayList<String>();
	}
	
	public Review(String reviewer,String reviewID, ArrayList<String> reviewOn, Date submitTime,
			String reviewText, double rating) {
		super();
		this.reviewer = reviewer;
		this.reviewID = reviewID;
		this.reviewOn = reviewOn;
		this.submitTime = submitTime;
		this.reviewText = reviewText;
		this.rating = rating;
	}
	
	public Review(String s){
		if(!s.equals("")){
			
			String[] userFields = s.split(";");
			reviewer = userFields[0].split(":")[1];
			reviewID = userFields[1].split(":")[1];
			String reviewOnListString = userFields[2].split(":")[1];  
			reviewOn = parseArrayListFromString(reviewOnListString);
//			submitTime = Date(userFields[3].split(":")[1]);
			reviewText = userFields[4].split(":")[1];
			rating = Double.valueOf(userFields[5].split(":")[1]);
			
			//System.out.println("New user: " + this.toString());
			
		}else{
			//it always should exist!!!
		}
	}
	
	public String toString(){
		String output = "reviewer:"+reviewer+";"
		+"#reviewOn:"+reviewOn.toString()+";"
		+"#submitTime:"+submitTime+";"
		+"#reviewText:"+reviewText+";"
		+"#rating:"+rating+"#";
		return output;
	}

	private ArrayList<String> parseArrayListFromString(String s){
		ArrayList<String> t = new ArrayList<String>();
		if (!s.equals("[]")) {
			String[] itemArray = s.split(",");
			//get rid of [ and ]
			itemArray[0] = itemArray[0].substring(1);
			itemArray[itemArray.length-1] = itemArray[itemArray.length-1].substring(0,itemArray[itemArray.length-1].length()-1);

			for (String item : itemArray) {
				t.add(item);
			}
		}
		return t;
	}
	public static ArrayList<Review> getReviewFromReviewString(SharedPreferences sp,String reviewListString){
		ArrayList<Review> userReviews = new ArrayList<Review>();
		
		if (!reviewListString.equals("[]")) {
			String[] reviewListArray = reviewListString.split(",");
			//get rid of [ and ]
			reviewListArray[0] = reviewListArray[0].substring(1);
			reviewListArray[reviewListArray.length-1] = reviewListArray[reviewListArray.length-1].substring(0,reviewListArray[reviewListArray.length-1].length()-2);
			System.out.print(reviewListString);
			System.out.print(reviewListArray);
			
			for (String s : reviewListArray) {
				userReviews.add(new Review(sp.getString(s.trim(), "")));
			}
			
		}
		
		return userReviews;
		
	}





	
	public String getReviewID() {
		return reviewer+submitTime;
	}
	public void setReviewID(String reviewID) {
		this.reviewID = reviewID;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public ArrayList<String> getReviewOn() {
		return reviewOn;
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
