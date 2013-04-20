package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Map;

import android.content.SharedPreferences;

public class App {
	private String name;
	private String appID;
	private String description;
	private String icon;
	private String apk;
	private ArrayList<String> screenShots;
	private String screenShotsListString;
	private ArrayList<String> developerAsksFor;
	private String developerAsksForListString;
	private ArrayList<String> reviewList;
	private String reviewListString;
	private ArrayList<String> reviewers;
	private String reviewersListString;
	private double appRate;
	private double minPay;
	private double maxPay;
	private int reviewCounter;
	
	
	
	public App(String name, String appID, String description, String icon,
			String apk,	double appRate, double minPay, double maxPay,
			int reviewCounter) {
		super();
		this.name = name;
		this.appID = appID;
		this.description = description;
		this.icon = icon;
		this.apk = apk;
		this.screenShots =  new ArrayList<String>();
		this.developerAsksFor = new ArrayList<String>();
		this.reviewList = new ArrayList<String>();
		this.reviewers = new ArrayList<String>();
		this.appRate = appRate;
		this.minPay = minPay;
		this.maxPay = maxPay;
		this.reviewCounter = reviewCounter;
	}

	//s is everything that defines app, see toString (from name to reviewCounter variable).
	public App(String s) {
		
		System.out.println("input to app: " + s);
		if(!s.equals("")){
			String[] appFields = s.split(";");

			name = appFields[0].split(":")[1];
			appID = appFields[1].split(":")[1];
			description = appFields[2].split(":")[1];
			icon = appFields[3].split(":")[1];
			apk = appFields[4].split(":")[1];
			
			screenShotsListString = appFields[5].split(":")[1];
			screenShots = parseArrayListFromString(screenShotsListString);
			
			developerAsksForListString = appFields[6].split(":")[1];
			developerAsksFor = parseArrayListFromString(developerAsksForListString);
			
			reviewListString = appFields[7].split(":")[1];
			reviewList = parseArrayListFromString(reviewListString);
			
			reviewersListString = appFields[8].split(":")[1];
			reviewers =  parseArrayListFromString(reviewersListString);
			
			appRate = Double.parseDouble(appFields[9].split(":")[1]);
			minPay = Double.parseDouble(appFields[10].split(":")[1]);
			maxPay = Double.parseDouble(appFields[11].split(":")[1]);
			reviewCounter = Integer.parseInt(appFields[12].split(":")[1]);
		}else{
			screenShots = new ArrayList<String>();
			developerAsksFor = new ArrayList<String>();
			reviewList = new ArrayList<String>();;
			reviewers  = new ArrayList<String>();;
		}
	}
	public String toString(){

		String outPut = "name:"+name+";"
		+"appID:"+appID+";"
		+"description:"+description+";"
		+"icon:"+icon+";"
		+"apk:"+apk+";"
		+"screenShots:"+screenShots.toString()+";"
		+"developerAsksFor:"+developerAsksFor.toString()+";"
		+"reviewList:"+reviewList.toString()+";"
		+"reviewers:" + reviewers.toString()+";"
		+"appRate:"+appRate+";"
		+"minPay:"+minPay+";"
		+"maxPay:"+maxPay+";"
		+"reviewCounter:"+reviewCounter;
		
		return outPut;
	}
	
	public static ArrayList<App> getAppsFromAppString(SharedPreferences sp,String appListString){
		ArrayList<App> userApps = new ArrayList<App>(); 
		if (!appListString.equals("[]")) {
			String[] appListArray = appListString.split(",");
			//get rid of [ and ]
			appListArray[0] = appListArray[0].substring(1);
			appListArray[appListArray.length-1] = appListArray[appListArray.length-1].substring(0,appListArray[appListArray.length-1].length()-1);
			
			System.out.print(appListString);
			System.out.print(appListArray);
			//Map<String,?> all = sp.getAll();
			for (String s : appListArray) {
				
				String out = sp.getString(s.trim(), "");
				userApps.add(new App(out));
			}
		}
		return userApps;
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
	
	public String getReviewListString() {
		return reviewList.toString();
	}
	public String getDeveloperAsksForListString() {
		return developerAsksFor.toString();
	}
	public String getReviewersListString() {
		return reviewers.toString();
	}
	public String getAppID() {
		return appID;
	}
	public String getScreenShotsListString() {
		return screenShots.toString();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAppID(String appID) {
		this.appID = appID;
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
	
	public ArrayList<String> getDeveloperAsksFor() {
		return developerAsksFor;
	}
	
	public void addDeveloperAsksFor(String developerAsksForItem) {
		this.developerAsksFor.add(developerAsksForItem);
	}
	public ArrayList<String> getReviewList() {
		return reviewList;
	}
	public void addReviewList(String review) {
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