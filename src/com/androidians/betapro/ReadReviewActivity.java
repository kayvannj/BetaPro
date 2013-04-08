package com.androidians.betapro;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ReadReviewActivity extends Activity implements SeekBar.OnSeekBarChangeListener{
	
	
	TextView progView;
	Dialog dialog;
	
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
		  
		  SeekBar bar = (SeekBar) dialog.findViewById(R.id.seekbar);
		  bar.setMax(10);
		  bar.setOnSeekBarChangeListener(this);
		
		Button rateBtn = (Button) findViewById(R.id.rateBtn);
		TextView tv1 =(TextView) findViewById(R.id.detailView1);
		TextView tv2=(TextView)findViewById(R.id.detailView2);
		tv1.setText("Detail Title Goes Here");
		tv2.setText("Detail Content Goes Here");
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
		progView.setTextSize(28);
		progView.setTextColor(Color.parseColor("#FF924A"));
		progView.setText("Suggested Pay: $"+progress);
		
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
