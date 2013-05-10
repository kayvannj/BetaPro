package com.androidians.betapro;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReadReviewActivity extends Activity implements
		SeekBar.OnSeekBarChangeListener {

	TextView progView;
	Dialog dialog;
	User activeUser;
	App workingApp;
	int progress = 0;
	User reviewer;
	private static SharedPreferences preferences;
	private static SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.read_review);
		
		dialog = new Dialog(ReadReviewActivity.this);
		dialog.setContentView(R.layout.rating_dialog);
		dialog.setTitle("Rate and Pay");
		dialog.setCancelable(true);
		  
		final SeekBar bar = (SeekBar) dialog.findViewById(R.id.seekbar); // money to pay
		final RatingBar rbar = (RatingBar) dialog.findViewById(R.id.rating_pay); //star bar
		final EditText field = (EditText) dialog.findViewById(R.id.dialogEditText);//edit text
		final RatingBar readRatingBar = (RatingBar) findViewById(R.id.read_ratingBar);
	
		
		Button rateBtn = (Button) findViewById(R.id.rateBtn);
		Button backBtn=(Button)findViewById(R.id.readBackBtn);
		TextView tv1 =(TextView) findViewById(R.id.detailView1);
		TextView tv2=(TextView)findViewById(R.id.detailView2);
		TextView tv3=(TextView)findViewById(R.id.detailView3);
		TextView for_view =(TextView)findViewById(R.id.for_view);
		
		Intent lastIntent = getIntent();
		String description = lastIntent.getStringExtra("details");
		Log.d("extra",lastIntent.getStringExtra("title"));
		String title = "By Reviewer "+lastIntent.getStringExtra("title");
		
		String reviewOn = lastIntent.getStringExtra("reviewOn");
		final String userString = lastIntent.getStringExtra("theUser");
		final String reviewerString = lastIntent.getStringExtra("theReviewer");
		final String appID = lastIntent.getStringExtra("app");
		
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		activeUser = new User(preferences.getString(userString, ""));
		editor = preferences.edit();
		
		for (App app : App.getAppsFromAppString(Login.getSharedPreferences(this), activeUser.getAppListString())) {
			if (appID.equals(app.getName())) {
				workingApp = app;
			}
		}
		bar.setMax((int) ((workingApp.getMaxPay()-workingApp.getMinPay())*100));
		TextView min = (TextView)dialog.findViewById(R.id.grid1);
		TextView mid = (TextView)dialog.findViewById(R.id.grid2);
		TextView max = (TextView)dialog.findViewById(R.id.grid3);
		min.setText(String.valueOf(workingApp.getMinPay()));
		mid.setText(String.valueOf((workingApp.getMaxPay()+workingApp.getMinPay())/2));
		max.setText(String.valueOf(workingApp.getMaxPay()));
		
		
		HashSet<String> emptySet = new HashSet<String>();
		HashSet<String> allIDs = (HashSet<String>) Login.getSharedPreferences(this).getStringSet(Login.USERS_KEY, emptySet);
		Iterator<String> itr = allIDs.iterator();
		ArrayList<User> allUsers = new ArrayList<User>();
		
		//Loading All the users
		while(itr.hasNext()) {
			String userInfo = Login.getSharedPreferences(this).getString((String) itr.next(), "");
			allUsers.add(new User(userInfo));
		}
		
		for(User u:allUsers) {
			if(u.getUserID().equals(reviewerString)) {
				reviewer = u;
			}
		}
		
		//bar.setMax((int) workingApp.getMaxPay());
		
		title = reviewer.getUsername()+" rated:";
	
		bar.setOnSeekBarChangeListener(this);
		String d = lastIntent.getStringExtra("rating");
		if (!(d==null)){
		readRatingBar.setRating(Float.parseFloat(d));
		}
		
		tv1.setText(title);
		tv2.setText(description);
		
		
		if (!reviewOn.equals("[]")){
			//String html ="&#8226; foo<br/>"+
			//"&#8226; bar<br/>"+
		   // "&#8226; baz<br/>";
			
			
	    for_view.setText("Regarding:");
	    String[] list = reviewOn.substring(1, reviewOn.length()-1).split(",");
	    list[0]=" "+list[0];
	    String html="&#8226";
	    for (int i=0;i<list.length;i++){
	    	html+=list[i]+"<br>"+"&#8226";
	    }
	    
	    
		//tv3.setText(reviewOn);
	  tv3.setText(Html.fromHtml(html.substring(0,html.length()-6)));
		}
		// Set up the action bar to show tabs and hide title.
		backBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		rateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  // TODO Auto-generated method stub
			  progView = (TextView) dialog.findViewById(R.id.progress);
			  if (workingApp!=null){
				    progView.setTextSize(28);
					progView.setTextColor(Color.parseColor("#FF924A"));
					progView.setText("$"+workingApp.getMinPay());
					}
			  
			  
			  
			  Button payBtn = (Button) dialog.findViewById(R.id.payBtn);
			  
			  payBtn.setOnClickListener(new OnClickListener() {
	                @Override
	                    public void onClick(View v) {
	                	
	                	   if (field.getText().toString().equals("")){
	                		Transaction t = new Transaction(userString+reviewerString+System.currentTimeMillis(),
	                				userString, reviewerString, System.currentTimeMillis(), workingApp.getMinPay()+(double)bar.getProgress()/100, appID, 0, false);
	                		reviewer.addTransaction(t.getTransactionID());
	                		
	                		//editor.putString(reviewer.getUserID(), reviewer.toString());
							//editor.putString(t.getTransactionID(), t.toString());
							//editor.commit();
	                		//Transaction trans = new Transaction(activeUser.getUsername(), reviewerString, new Date(), (double)bar.getProgress(), workingApp.getName(),0 , false);
	                		t.commit(preferences);
	                		//activeUser.addTransaction(trans);
	                		
	                		Toast.makeText(getApplicationContext(), "You have paid "+reviewer.getUsername()+" an amount of $"+(workingApp.getMinPay()+(double)bar.getProgress()/100), Toast.LENGTH_LONG).show();
	                	   }
	                	   
	                	   else{
	                		   Transaction t = new Transaction(userString+reviewerString+System.currentTimeMillis(),
		                				userString, reviewerString, System.currentTimeMillis(), workingApp.getMinPay()+(double)Double.parseDouble(field.getText().toString()), appID, 0, false);
	                		   reviewer.addTransaction(t.getTransactionID());
	                		   t.commit(preferences);
	                		   Toast.makeText(getApplicationContext(), "You have paid "+reviewer.getUsername()+" an amount of $"+((double)Double.parseDouble(field.getText().toString())), Toast.LENGTH_LONG).show();
	                		   
	                	   }
	                		dialog.cancel();
	                    }
	                });
			  
			  dialog.show();
			  
				
			}
		});
		field.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
		field.addTextChangedListener(new TextWatcher(){
				public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
             }
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
				if (s.toString().equals("")){
					return;
				}
				
				if (Double.parseDouble(s.toString())>workingApp.getMaxPay()||Double.parseDouble(s.toString())<workingApp.getMinPay()){
					field.setError("Please enter a number between "+ workingApp.getMinPay()+" and "+ workingApp.getMaxPay());
					return;
				}
				progView.setTextSize(28);
				progView.setTextColor(Color.parseColor("#FF924A"));
				//DecimalFormat df = new DecimalFormat("0.00");
				progView.setText("$"+ Double.parseDouble(s.toString()));
				bar.setProgress((int)(Double.parseDouble(s.toString())*(workingApp.getMaxPay()-workingApp.getMinPay())*100/workingApp.getMaxPay()));
			}

        });
		
		
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		progView.setTextSize(28);
		progView.setTextColor(Color.parseColor("#FF924A"));
		DecimalFormat df = new DecimalFormat("0.00");
		progView.setText("$"
				+ df.format(workingApp.getMinPay() + (double) progress / 100));
		this.progress = progress;

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, Home.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(Login.CURRENT_USER, activeUser.getUserID());
			startActivity(intent);
			return true;

		case R.id.help:
			Intent helpIntent = new Intent(this, doc.class);
			startActivity(helpIntent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
