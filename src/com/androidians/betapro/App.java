package com.androidians.betapro;

import java.util.ArrayList;

public class App {
	private String name;
	private String appId;
	private String desctiption;
	private String icon;
	private String apk;
	private ArrayList<String> screenShots;
	private ArrayList<String> developerAsksFor;
	private ArrayList<Review> reviewList;
	private User developer;
	private double appRate;
	private double minPay;
	private double maxPay;
	private int reviewCounter;
	
	
	
	public App(String name, String appId, String desctiption, String icon,
			String apk, ArrayList<String> screenShots,
			ArrayList<String> developerAsksFor, ArrayList<Review> reviewList,
			User developer, double appRate, double minPay, double maxPay,
			int reviewCounter) {
		super();
		this.name = name;
		this.appId = appId;
		this.desctiption = desctiption;
		this.icon = icon;
		this.apk = apk;
		this.screenShots = screenShots;
		this.developerAsksFor = developerAsksFor;
		this.reviewList = reviewList;
		this.developer = developer;
		this.appRate = appRate;
		this.minPay = minPay;
		this.maxPay = maxPay;
		this.reviewCounter = reviewCounter;
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
	public String getDesctiption() {
		return desctiption;
	}
	public void setDesctiption(String desctiption) {
		this.desctiption = desctiption;
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
	public User getDeveloper() {
		return developer;
	}
	public void setDeveloper(User developer) {
		this.developer = developer;
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
