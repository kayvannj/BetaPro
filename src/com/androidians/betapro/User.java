package com.androidians.betapro;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class User {
	private String username;
	private String password;
	private String userID;
	private ArrayList<String> appList;
	String appListString;
	private ArrayList<String> transactionList;
	String transactionListString;
	private double balance;
	private double reviewerRate;
	

	public User(String username, String password, String userID) {
		super();
		this.username = username;
		this.password = password;
		this.userID = userID;
		this.appList = new ArrayList<String>();
		this.appListString = "";
		this.transactionListString = "";
		this.transactionList = new ArrayList<String>();
		this.balance = 0;
		this.reviewerRate = 0;
	}

	public User(){}
	public User(String s) {
		/*Parsing s and creating new user based on the given data*/
		
		System.out.println("Creating User from string: "+s);
		if(!s.equals("")){
			
			String[] userFields = s.split(";");
			username = userFields[0].split(":")[1];
			password = userFields[1].split(":")[1];
			appListString = userFields[2].split(":")[1];  
			appList = parseArrayListFromString(appListString);
			transactionListString = userFields[3].split(":")[1];
			transactionList = parseArrayListFromString(transactionListString);
			balance = Double.valueOf(userFields[4].split(":")[1]);
			reviewerRate = Double.valueOf(userFields[5].split(":")[1]);
			userID = userFields[6].split(":")[1];
			//System.out.println("New user: " + this.toString());
			
		}else{
			//it always should exist!!!
		}
		
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
	public String toString(){
		String output = "username:"+username+";"
				+"password:"+password+";"
				+"appList:"+appList.toString()+";"
				+"transactionList:"+transactionList.toString()+";"
				+"balance:"+balance+";"
				+"reviewerRate:"+reviewerRate+";"
				+"userID:"+userID;
		return output;
	}

	public boolean addApp(String app) {
		if (appList==null) {
			appList = new ArrayList<String>();
		}
		appList.add(app);
		return true;
	}
	public boolean addTransaction(String trans) {
		if (transactionList==null) {
			transactionList = new ArrayList<String>();
		}
		transactionList.add(trans);
		return true;
	}
	public String getTransactionListString() {
		return transactionList.toString();
	}
	public ArrayList<String> getAppList() {
		return appList;
	}

	public ArrayList<String> getTransactionList(){
		return transactionList;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getReviewerRate() {
		return reviewerRate;
	}
	public void setReviewerRate(double reviewerRate) {
		this.reviewerRate = reviewerRate;
	}
	public String getAppListString() {
		if (appList==null) {
			appList = new ArrayList<String>();
		}
		return appList.toString();
	}
	public void setAppListString(String appListString) {
		this.appListString = appListString;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
