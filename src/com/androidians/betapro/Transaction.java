package com.androidians.betapro;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.SharedPreferences;

public class Transaction {
	private String transactionID;
	private String source;
	private String destination;
	private Long transactionTime;
	private double amount;
	private boolean committed;
	private String appName;
	private int valuePercentage;
	private boolean read;

	public Transaction(String transactionID,String source, String destination, Long transactionTime,
			double amount, String appName, int valuePercentage, boolean read) {
		super();
		this.transactionID = transactionID;
		this.source = source;
		this.destination = destination;
		this.transactionTime = transactionTime;
		this.amount = amount;
		this.committed = false;
		this.appName = appName;
		this.valuePercentage = valuePercentage;
		this.read = read;
	}

	public Transaction(String s){
		super();
		System.out.println("Creating Transaction from string " + s);

		if(!s.equals("")){
			String[] transactionFields = s.split(";");
			source = transactionFields[0].split(":")[1];
			destination = transactionFields[1].split(":")[1];
			transactionTime = Long.parseLong(transactionFields[2].split(":")[1]);
			amount = Double.parseDouble(transactionFields[3].split(":")[1]);
			committed = Boolean.parseBoolean(transactionFields[4].split(":")[1]);
			appName = transactionFields[5].split(":")[1];
			valuePercentage = 0; //Not implemenred
			read = Boolean.parseBoolean(transactionFields[7].split(":")[1]);
			transactionID = transactionFields[8].split(":")[1];

		}

	}
	public String toString(){
		String output = "source:"+source+";"
				+"destination:"+destination+";"
				+"transactionTime:"+transactionTime+";"
				+"amount:"+amount+";"
				+"committed:"+committed+";"
				+"appName:"+appName+";"
				+"valuePercentage:"+valuePercentage+";"
				+"read:"+read+";"
				+"transactionID:"+transactionID;
		return output;
	}
	
	
	
	public static ArrayList<Transaction> getTransactionFromTransactionString(SharedPreferences sp,String TransactionListString){
		ArrayList<Transaction> userTransactions = new ArrayList<Transaction>();
		
		if (!TransactionListString.equals("[]")) {
			String[] TransactionListArray = TransactionListString.split(",");
			//get rid of [ and ]
			TransactionListArray[0] = TransactionListArray[0].substring(1);
			TransactionListArray[TransactionListArray.length-1] = TransactionListArray[TransactionListArray.length-1].substring(0,TransactionListArray[TransactionListArray.length-1].length()-1);
			System.out.print(TransactionListString);
			System.out.print(TransactionListArray);
			
			for (String s : TransactionListArray) {
				userTransactions.add(new Transaction(sp.getString(s.trim(), "")));
			}
		
		}
		
		return userTransactions;
		
	}
	public String getTransactionID() {
		return source+destination+transactionTime;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public boolean commit(SharedPreferences sp){
		//do the transaction (send money from source to destination)
		SharedPreferences.Editor editor = sp.edit(); 
		
		User fromUser = new User(sp.getString(source,""));
		User toUser = new User(sp.getString(destination,""));
		fromUser.setBalance(fromUser.getBalance()-amount);
		
		toUser.setBalance(toUser.getBalance()+amount);
		
		toUser.addTransaction(getTransactionID());
		
		editor.putString(fromUser.getUserID(), fromUser.toString());
		editor.putString(toUser.getUserID(), toUser.toString());
		
		committed = true;
		
		editor.putString(getTransactionID(), this.toString());
		
		editor.commit();
		//to do

		
		return true;
	}





	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Long transactionTime) {
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
	public void setRead(boolean read,SharedPreferences.Editor editor) {
		
		this.read = read;
		editor.putString(transactionID, toString());
		editor.commit();
		
	}

}
