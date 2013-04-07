package com.androidians.betapro;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class ReadReviewActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.read_review);
		
		
		Button rateBtn = (Button) findViewById(R.id.rateBtn);
		// Set up the action bar to show tabs and hide title.
		rateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  Dialog dialog = new Dialog(ReadReviewActivity.this);
			  dialog.setContentView(R.layout.rating_dialog);
			  dialog.setTitle("Pay and Rate");
			  dialog.setCancelable(true);
			  
			  SeekBar bar = (SeekBar) dialog.findViewById(R.id.seekbar);
			  bar.setMax(10);
			  
			  Button payBtn = (Button) dialog.findViewById(R.id.payBtn);
			  payBtn.setOnClickListener(new OnClickListener() {
	                @Override
	                    public void onClick(View v) {
	                        finish();
	                    }
	                });
			  
			  dialog.show();
			  
				
			}
		});
		
	}
}
