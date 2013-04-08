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
		+"password:"+password+";"
		+"appList:"+(appList!= null?appList.toString():"")+";"
		+"transactionList:"+(transactionList !=null?transactionList.toString():"")+";"
		+"balance:"+balance+";"
		+"reviewerRate:"+reviewerRate;
		return output;
	}
	
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
