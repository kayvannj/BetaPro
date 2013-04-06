package com.androidians.betapro;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DeveloperMain extends FragmentActivity implements
ActionBar.TabListener {

	private static final int TAB_PUBLISH = 0;

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
		
	}
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// When the given tab is selected, show the tab contents in the
		// container view.
		
		Fragment publishPage1Fragment = new PublishPage1(); //the fragment that we want to create and show 
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_PUBLISH){ //check to see which tab has been selected
			transaction.replace(R.id.developer_container, publishPage1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
		}
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
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
	
}
