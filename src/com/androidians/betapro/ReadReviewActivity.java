package com.androidians.betapro;

import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ReadReviewActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
	
	
	TextView progView;
	Dialog dialog;
	User activeUser;
	App workingApp;
	int progress = 0;
	
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
		
		
		Button rateBtn = (Button) findViewById(R.id.rateBtn);
		TextView tv1 =(TextView) findViewById(R.id.detailView1);
		TextView tv2=(TextView)findViewById(R.id.detailView2);
		
		Intent lastIntent = getIntent();
		String description = lastIntent.getStringExtra("details");
		Log.d("extra",lastIntent.getStringExtra("title"));
		String title = "By Reviewer "+lastIntent.getStringExtra("title");
		String userString = lastIntent.getStringExtra("theUser");
		final String reviewerString = lastIntent.getStringExtra("theReviewer");
		activeUser = new User(userString);
		
		for (App app : App.getAppsFromAppString(Login.getSharedPreferences(this), activeUser.getAppListString())) {
			if (lastIntent.getStringExtra("title").equals(app.getName())) {
				workingApp = app;
			}
		}
		
		
		//bar.setMax((int) workingApp.getMaxPay());
		
		bar.setOnSeekBarChangeListener(this);
		
		tv1.setText(title);
		tv2.setText(description);
		// Set up the action bar to show tabs and hide title.
		rateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  progView = (TextView) dialog.findViewById(R.id.progress);
			  Button payBtn = (Button) dialog.findViewById(R.id.payBtn);
			  
			  payBtn.setOnClickListener(new OnClickListener() {
	                @Override
	                    public void onClick(View v) {
	                		activeUser.setReviewerRate(rbar.getProgress());
	                		//Transaction trans = new Transaction(activeUser.getUsername(), reviewerString, new Date(), (double)bar.getProgress(), workingApp.getName(),0 , false);
	                		//trans.commit();
	                		//activeUser.addTransaction(trans);
	                		
	                		Toast.makeText(getApplicationContext(), "You have paid "+reviewerString+" an amount of $"+bar.getProgress(), Toast.LENGTH_LONG).show();
	                		
	                		dialog.cancel();
	                    }
	                });
			  
			  dialog.show();
			  
				
			}
		});
		
	}



	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		/*progView.setTextSize(28);
		progView.setTextColor(Color.parseColor("#FF924A"));
		progView.setText("Suggested Pay: $"+progress);*/
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


	
	
}
