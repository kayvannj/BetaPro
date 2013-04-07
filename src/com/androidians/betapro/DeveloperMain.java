package com.androidians.betapro;


import java.util.ArrayList;

import java.io.InputStream;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.EditText;


public class DeveloperMain extends FragmentActivity implements ActionBar.TabListener {
	
	
	static MyAppsListAdapter appsListAdapter;
	static ExpandableListView myAppsListView;

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static final int TAB_PUBLISH = 0;
	private static final int TAB_MYAPPS =1;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.developer_container_layout);
		// Set up the action bar to show tabs and hide title.
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
		

		App testApp1= new App("test app 1", "", "", "","",	0, 0, 0,0); 
		App testApp2 = new App("test app 2", "", "", "","",	0, 0, 0,0); 
		ArrayList<App> testAppList = new ArrayList<App>();
		testAppList.add(testApp1);
		testAppList.add(testApp2);
		appsListAdapter = new MyAppsListAdapter(this,testAppList);
		
		
		
		}


	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// When the given tab is selected, show the tab contents in the
		// container view.
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.d("Tab selected", "tab has been selected"); 
		Fragment publishPage1Fragment = new PublishPage1(); //the fragment that we want to create and show 
		Fragment myApps1Fragment = new MyApps1();
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_PUBLISH){ //check to see which tab has been selected
			Log.d("Tab selected", "publish tab");
			transaction.replace(R.id.developer_container, publishPage1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			
			
			transaction.commit();
		}
		if(tab.getPosition()==TAB_MYAPPS){ //check to see which tab has been selected
			Log.d("Tab selected", "myapps tab");
			transaction.replace(R.id.developer_container,myApps1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
			
			
			
			
		}

		
	}



	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public static class PublishPage1 extends Fragment{
		private static final int SCREEN_SHOT1 = 1;
		private static final int SCREEN_SHOT2 = 2;
		private static final int ICON = 3;
		private static final int APK = 4;

		public PublishPage1() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			return inflater.inflate(R.layout.publish_page1, container, false);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			
			super.onActivityCreated(savedInstanceState);
			
			//an intent for file browsing
	        final Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_GET_CONTENT);
	        intent.addCategory(Intent.CATEGORY_OPENABLE);
	        intent.setType("file/*");
	        
	        
	        
	        // SCREEN_SHOT1 browse
			Button browseScreenShot1= (Button)this.getView().findViewById(R.id.screenShot1_bt);
			browseScreenShot1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, SCREEN_SHOT1);
				}
			});
			
	        // SCREEN_SHOT2 browse
			Button browseScreenShot2= (Button)this.getView().findViewById(R.id.screenShot2_bt);
			browseScreenShot2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, SCREEN_SHOT2);
				}
			});
			
			// ICON browse
			Button browseIcon= (Button)this.getView().findViewById(R.id.icon_bt);
			browseIcon.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, ICON);
				}
			});
			
			// APK browse
			Button browseApk= (Button)this.getView().findViewById(R.id.apk_bt);
			browseApk.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, APK);
				}
			});
			
			
			
		}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		EditText screenShot1Uri = (EditText)this.getView().findViewById(R.id.screenShot1_et);
		EditText screenShot2Uri = (EditText)this.getView().findViewById(R.id.screenShot2_et);
		EditText iconUri = (EditText)this.getView().findViewById(R.id.icon_et);
		EditText apkUri= (EditText)this.getView().findViewById(R.id.apk_et);
		
		// Getting the result of Intent
		// ScreenShot 1 and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==SCREEN_SHOT1) {
			screenShot1Uri.setText(data.getData().getPath());
		}
		// ScreenShot 2 and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==SCREEN_SHOT2) {
			screenShot2Uri.setText(data.getData().getPath());
		}
		// icon and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==ICON) {
			iconUri.setText(data.getData().getPath());
		}
		// apk and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==APK) {
			apkUri.setText(data.getData().getPath());
		}
	}
}

	
		
		
		

	public static class MyApps1 extends Fragment implements OnChildClickListener{


		public MyApps1() {
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			return inflater.inflate(R.layout.my_apps1, container, false);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			
			super.onActivityCreated(savedInstanceState);

			
			myAppsListView = (ExpandableListView) this.getView().findViewById(R.id.appList);
			
			myAppsListView.setAdapter(appsListAdapter);
		
			
			 
			 myAppsListView.setOnChildClickListener(this);
				}

	
		@Override
		public boolean onChildClick(ExpandableListView arg0, View arg1,
				int arg2, int arg3, long arg4) {
			// TODO Auto-generated method stub
			 Log.d("myapps", "onclickclick");
			 final Intent appIntent = new Intent(getActivity(),ReadReviewActivity.class);
		        startActivity(appIntent);
		        return false;
		
		}
	}
	
}
