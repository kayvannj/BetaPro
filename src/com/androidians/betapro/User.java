package com.androidians.betapro;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private ArrayList<App> appList;
	private ArrayList<Transaction> transactionList;
	private double balance;
	private double reviewerRate;
	
	
	
	public User(String username, String password, ArrayList<App> appList,
			ArrayList<Transaction> transactionList, double balance,
			double reviewerRate) {
		super();
		this.username = username;
		this.password = password;
		this.appList = appList;
		this.transactionList = transactionList;
		this.balance = balance;
		this.reviewerRate = reviewerRate;
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
