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
	private Date transactionTime;
	private double amount;
	private boolean committed;
	private String appName;
	private int valuePercentage;
	private boolean read;

	public Transaction(String transactionID,String source, String destination, Date transactionTime,
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

		if(s.equals("")){

		}else{
			String[] transactionFields = s.split(";\\$");
			source = transactionFields[0].substring(7);
			destination = transactionFields[1].substring(12);
			String temp = transactionFields[2].substring(16);
			try {
				transactionTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(temp);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			amount = Double.valueOf(transactionFields[3].substring(7));
			committed = (transactionFields[4].substring(10).equals("true"));
			appName = transactionFields[5].substring(8);
			valuePercentage = Integer.valueOf(transactionFields[6].substring(16));
			read = (transactionFields[7].substring(5).equals("true"));
		}
	}
	public String toString(){
		String output = "source:"+source+";"
				+"$destination:"+destination+";"
				+"$transactionTime:"+transactionTime+";"
				+"$amount:"+amount+";"
				+"$committed:"+committed+";"
				+"$appName:"+appName+";"
				+"$valuePercentage:"+valuePercentage+";"
				+"$read:"+read+"$";
		return output;
	}
	
	
	
	public static ArrayList<Transaction> getTransactionFromTransactionString(SharedPreferences sp,String TransactionListString){
		ArrayList<Transaction> userTransactions = new ArrayList<Transaction>();
		
		if (!TransactionListString.equals("[]")) {
			String[] TransactionListArray = TransactionListString.split(",");
			//get rid of [ and ]
			TransactionListArray[0] = TransactionListArray[0].substring(1);
			TransactionListArray[TransactionListArray.length-1] = TransactionListArray[TransactionListArray.length-1].substring(0,TransactionListArray[TransactionListArray.length-1].length()-2);
			System.out.print(TransactionListString);
			System.out.print(TransactionListArray);
			
			for (String s : TransactionListArray) {
				userTransactions.add(new Transaction(sp.getString(s, "")));
			}
		
		}
		
		return userTransactions;
		
	}
	public String getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
	public boolean commit(){
		//do the transaction (send money from source to destination)

		//to do

		committed = true;
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
