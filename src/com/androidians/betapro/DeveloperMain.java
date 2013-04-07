package com.androidians.betapro;

import java.util.ArrayList;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DeveloperMain extends FragmentActivity implements ActionBar.TabListener {
	
	
	static MyAppsListAdapter appsListAdapter;
	static ListView myAppsListView;

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
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// When the given tab is selected, show the tab contents in the
		// container view.
		
		Fragment publishPage1Fragment = new PublishPage1(); //the fragment that we want to create and show 
		Fragment myApps1Fragment = new MyApps1();
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_PUBLISH){ //check to see which tab has been selected
			
			transaction.replace(R.id.developer_container, publishPage1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			
			
			transaction.commit();
		}
		if(tab.getPosition()==TAB_MYAPPS){ //check to see which tab has been selected
		   
			transaction.replace(R.id.developer_container,myApps1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
			
			
			
			
		}
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
		if (tab.getPosition()==TAB_MYAPPS){
			Fragment myApps1Fragment = new MyApps1();
			android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
			transaction.replace(R.id.developer_container,myApps1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
			

			
			
		}
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public static class PublishPage1 extends Fragment{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		
		public PublishPage1() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			
			return inflater.inflate(R.layout.publish_page1, container, false);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			
			super.onActivityCreated(savedInstanceState);

			Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			

			
		}
	}
	public static class MyApps1 extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
	
		
		
		
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

			Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			myAppsListView = (ListView) this.getView().findViewById(R.id.appList);
			appsListAdapter.setListView(myAppsListView);
			myAppsListView.setAdapter(appsListAdapter);
		
			 final Intent appIntent = new Intent(getActivity(),ReadReviewActivity.class);
			 myAppsListView.setOnItemClickListener(new OnItemClickListener() {
					
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			        startActivity(appIntent);
			            
			        }

				});
				 
				 
			
			 
			 
		
			
			
			

			
		}
	}
	
}
