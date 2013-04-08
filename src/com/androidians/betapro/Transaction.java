package com.androidians.betapro;

import java.util.Date;

public class Transaction {
	private User source;
	private User destination;
	private Date transactionTime;
	private double amount;
	private boolean committed;
	private String appName;
	private int valuePercentage;
	private boolean read;
	
	public Transaction(User source, User destination, Date transactionTime,
			double amount, String appName, int valuePercentage, boolean read) {
		super();
		this.source = source;
		this.destination = destination;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.committed = false;
		this.appName = appName;
		this.valuePercentage = valuePercentage;
		this.read = read;
		
	}
	public String toString(){
		String output = "source:"+source+";"
		+"destination:"+destination+";"
		+"transactionTime:"+transactionTime+";"
		+"amount:"+amount+";"
		+"committed:"+committed+";"
		+"appName:"+appName+";"
		+"valuePercentage:"+valuePercentage+";"
		+"read:"+read;
		return output;
	}
	
	public boolean commit(){
		//do the transaction (send money from source to destination)

		//to do
		
		committed = true;
		return true;
	}
	
	
	
	
	
	public User getSource() {
		return source;
	}
	public void setSource(User source) {
		this.source = source;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getDestination() {
		return destination;
	}
	public void setDestination(User destination) {
		this.destination = destination;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	public boolean isCommitted() {
		return committed;
	}
	public void setCommitted(boolean committed) {
		this.committed = committed;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String an) {
		appName = an;
	}
	public int getValuePercentage() {
		return valuePercentage;
	}
	public void setValuePercentage(int valuePercentage) {
		this.valuePercentage = valuePercentage;
	}
	public boolean getRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
}
