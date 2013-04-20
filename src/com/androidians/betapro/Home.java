package com.androidians.betapro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Home extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		final Intent devMainIntent = new Intent(this, DeveloperMain.class);
		final Intent revMainIntent = new Intent(this, ReviewerMain.class);
		Bundle b = getIntent().getExtras();
		devMainIntent.putExtra(Login.CURRENT_USER, b.getString(Login.CURRENT_USER));
		revMainIntent.putExtra(Login.CURRENT_USER, b.getString(Login.CURRENT_USER));
		
		Button developerBt = (Button)findViewById(R.id.developer_bt);
		developerBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(devMainIntent);
			}
		});
		
		
		Button reviewerBt = (Button)findViewById(R.id.reviewer_bt);
		reviewerBt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(revMainIntent);
			}
		});
		
	}



}
