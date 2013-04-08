package com.androidians.betapro;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<App> appList;
	private ArrayList<Transaction> transactionList;
	private double balance;
	private double reviewerRate;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.appList = new ArrayList<App>();
		this.transactionList = new ArrayList<Transaction>();
		this.balance = 0;
		this.reviewerRate = 0;
	}
	
	public String toString(){
		String output = "username:"+username+";"
		+"|password:"+password+";"
		+"|appList:"+(appList!= null?appList.toString():"")+";"
		+"|transactionList:"+(transactionList !=null?transactionList.toString():"")+";"
		+"|balance:"+balance+";"
		+"|reviewerRate:"+reviewerRate;
		return output;
	}
	
	public void fillUser(String s) {
		String[] userFields = s.split(";\\|");
		String appListField = userFields[2];
		String appListFld = appListField.substring(9,appListField.length()-2);
		String[] apps = appListFld.split("@, ");
		for(String a: apps) {
			appList.add(new App(a));
		}
		String[] transactions = userFields[3].split("$, ");
//		for(String t:transactions) {
//			transactionList.add(new Transaction(t));
//		}
		System.out.println("input to transactionlist: " + userFields[3]);
		balance = Double.valueOf(userFields[4].substring(8));
		reviewerRate = Double.valueOf(userFields[5].substring(13));
		System.out.println("New user: " + this.toString());
	}
//	private void splitAppList(String s) {
//		ArrayList<Integer> openP = new ArrayList<Integer>();
//		ArrayList<Integer> closeP = new ArrayList<Integer>();
//		for(int i = 0; i<s.length(); i++) {
//			if(s.charAt(i)=='[') {
//				openP.add(i);
//			} else if (s.charAt(i)==']') {
//				closeP.add(i);
//			}
//		}
//		
//		
//	}
	
	public boolean addApp(App app) {
		appList.add(app);
		return true;
	}
	public boolean addTransaction(Transaction trans) {
		transactionList.add(trans);
		return true;
	}
	
	public App[] getAppList() {
		return (App[]) appList.toArray();
	}
	
	public Transaction[] getTransactionList(){
		return (Transaction[])transactionList.toArray();
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
	
	

}
