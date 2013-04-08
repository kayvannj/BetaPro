package com.androidians.betapro;

import java.util.ArrayList;

public class App {
	private String name;
	private String appId;
	private String description;
	private String icon;
	private String apk;
	private ArrayList<String> screenShots;
	private ArrayList<String> developerAsksFor;
	private ArrayList<Review> reviewList;
	private ArrayList<String> reviewers;
	private double appRate;
	private double minPay;
	private double maxPay;
	private int reviewCounter;
	
	
	
	public App(String name, String appId, String description, String icon,
			String apk,	double appRate, double minPay, double maxPay,
			int reviewCounter) {
		super();
		this.name = name;
		this.appId = appId;
		this.description = description;
		this.icon = icon;
		this.apk = apk;
		this.screenShots =  new ArrayList<String>();
		this.developerAsksFor = new ArrayList<String>();
		this.reviewList = new ArrayList<Review>();
		this.reviewers = new ArrayList<String>();
		this.appRate = appRate;
		this.minPay = minPay;
		this.maxPay = maxPay;
		this.reviewCounter = reviewCounter;
	}

	//s is everything that defines app, see toString (from name to reviewCounter variable).
	public App(String s) {
		super();
		System.out.println("input to app: " + s);
		String[] appFields = s.split(";@");
		name = appFields[0].substring(5);
		appId = appFields[1].substring(6);
		description = appFields[2].substring(12);
		icon = appFields[3].substring(5);
		apk = appFields[4].substring(4);
		
		String temp = appFields[5];
		
		screenShots =  new ArrayList<String>();
		String[] screenShotsFields = temp.substring(13, temp.length()-1).split(", ");
		for(String ssf: screenShotsFields) {
			if(!ssf.equals(""))
				screenShots.add(ssf);
		}
		
		developerAsksFor = new ArrayList<String>();
		temp = appFields[6];
		String[] developerAskedForFields = temp.substring(18,temp.length()-1).split(", ");
		for(String daff: developerAskedForFields) {
			if(!daff.equals(""))
				developerAsksFor.add(daff);
		}
		reviewList = new ArrayList<Review>();
		temp = appFields[7];
//		String[] reviewListFields = temp.substring(12,temp.length()-1).split("#, ");
//		for(String rlf:reviewListFields) {
//			if(!rlf.equals(""))
//				reviewList.add(new Review(rlf));
//		}
		System.out.println("input string to reviewlist: " + temp);
		
		reviewers = new ArrayList<String>();
		temp = appFields[8];
		String[] reviewersFields = temp.substring(10,temp.length()-1).split(", ");
		for(String rf: reviewersFields) {
			if(!rf.equals(""))
				reviewers.add(rf);
		}
		appRate = Double.valueOf(appFields[9].substring(8));
		minPay = Double.valueOf(appFields[10].substring(7));
		maxPay = Double.valueOf(appFields[11].substring(7));
		reviewCounter = Integer.valueOf(appFields[12].substring(14));
	}
	public String toString(){
		String outPut = "name:"+name+";"
		+"@appId:"+appId+";"
		+"@description:"+description+";"
		+"@icon:"+icon+";"
		+"@apk:"+apk+";"
		+"@screenShots:"+screenShots.toString()+";"
		+"@developerAsksFor:"+developerAsksFor.toString()+";"
		+"@reviewList:"+reviewList.toString()+";"
		+"@reviewers" + reviewers.toString()+";"
		+"@appRate:"+appRate+";"
		+"@minPay:"+minPay+";"
		+"@maxPay:"+maxPay+";"
		+"@reviewCounter:"+reviewCounter +"@";
		
		return outPut;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getApk() {
		return apk;
	}
	public void setApk(String apk) {
		this.apk = apk;
	}
	public String[] getScreenShots() {
		return (String[])screenShots.toArray();
	}
	public void addScreenShots(String screenShot) {
		this.screenShots.add(screenShot);
	}
	
	public String[] getDeveloperAsksFor() {
		return (String[]) developerAsksFor.toArray();
	}
	
	public void addDeveloperAsksFor(String developerAsksForItem) {
		this.developerAsksFor.add(developerAsksForItem);
	}
	
	public Review[] getReviewList() {
		return (Review[]) reviewList.toArray();
	}
	public void addReviewList(Review review) {
		this.reviewList.add(review);
	}
	
	public ArrayList<String> getReviewers(){
		return reviewers;
	}
	
	public void addReviewer(String reviewer) {
		this.reviewers.add(reviewer);
	}

	public double getAppRate() {
		return appRate;
	}
	public void setAppRate(double appRate) {
		this.appRate = appRate;
	}
	public double getMinPay() {
		return minPay;
	}
	public void setMinPay(double minPay) {
		this.minPay = minPay;
	}
	public double getMaxPay() {
		return maxPay;
	}
	public void setMaxPay(double maxPay) {
		this.maxPay = maxPay;
	}
	public int getReviewCounter() {
		return reviewCounter;
	}
	public void setReviewCounter(int reviewCounter) {
		this.reviewCounter = reviewCounter;
	}
	
}