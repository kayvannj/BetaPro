package com.androidians.betapro;

import java.util.ArrayList;

import com.androidians.betapro.DeveloperMain.MyApps1;
import com.androidians.betapro.DeveloperMain.PublishPage1;
import android.app.FragmentTransaction;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ReadReviewActivity extends FragmentActivity implements TabListener {
	
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static final int TAB_PUBLISH = 0;
	private static final int TAB_MYAPPS =1;
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.read_review);
		
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.publish_tab)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.myapps_tab)// No action for this class yet
				.setTabListener(this));
		actionBar.setSelectedNavigationItem(TAB_PUBLISH);
		
		
		
		
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
			  final Dialog dialog = new Dialog(ReadReviewActivity.this);
			  dialog.setContentView(R.layout.rating_dialog);
			  dialog.setTitle("Rate and Pay");
			  dialog.setCancelable(true);
			  
			  SeekBar bar = (SeekBar) dialog.findViewById(R.id.seekbar);
			  bar.setMax(10);
			  
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
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		
					
					
					
					
				
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}
