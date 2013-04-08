//Written by Juntao Mao

package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

public class ReviewerMain extends FragmentActivity implements
ActionBar.TabListener  {
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "reviewer_main_selected_navigation_item";
	private static final int TAB_BROWSE = 0;
	private static final int TAB_WRITE =1;
	private static final int TAB_NOTIFICATION = 0;
	private static ArrayList<App> applist;
	private static User curUser;
	static String lastViewed;
	

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.reviewer_contain_layout);
		applist = new ArrayList<App>();
		//Bundle extras = this.getIntent().getExtras();
		//appList = extras.get
		// Set up the action bar to show tabs and hide title.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.browse_tab)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.writereview_tab)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.notification_tab)
				.setTabListener(this));
		actionBar.setSelectedNavigationItem(TAB_BROWSE);
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
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Fragment browseFragment = new BrowseApps(); 
		Fragment writeReviewFragment = new WriteReview();
		//Fragment notificationFragment = new Notification();
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_BROWSE){ //check to see which tab has been selected
			transaction.replace(R.id.reviewer_container, browseFragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
		}
				if(tab.getPosition()==TAB_WRITE){ //check to see which tab has been selected
		
					transaction.replace(R.id.reviewer_container,writeReviewFragment); // the container in Main page and the fragment so it starts the fragment in the container
					transaction.commit();
				}
		//		if(tab.getPosition()==TAB_NOTIFICATION){ //check to see which tab has been selected
		//
		//			transaction.replace(R.id.reviewer_container,notificationFragment); // the container in Main page and the fragment so it starts the fragment in the container
		//			transaction.commit();
		//		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public ArrayList<App> getAppList(){
		return applist;
	}


	public static class BrowseApps extends Fragment{
		ListView lv;

		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public BrowseApps() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// Create a new TextView and set its text to the fragment's section
			// number argument value.

			return inflater.inflate(R.layout.browse, container, false);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {

			super.onActivityCreated(savedInstanceState);



			//Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			lv = (ListView)getView().findViewById(R.id.browse_lv);

			AppListAdapter adapter = new AppListAdapter(getActivity(), ((ReviewerMain)getActivity()).getAppList()); 
			lv.setAdapter(adapter);
		}

		class AppListAdapter extends ArrayAdapter<App> {
			private final Context context;
			private final ArrayList<App> apps;

			public AppListAdapter(Context context,
					ArrayList<App> values) {
				super(context, R.layout.browse_app_adapter_item, values);
				this.context = context;
				this.apps = values;
			}

			// this method is called once for each item in the list
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View listItem = inflater.inflate(R.layout.browse_app_adapter_item,
						parent, false);

				TextView an = (TextView) listItem.findViewById(R.id.browse_app_title);
				an.setText(apps.get(position).getName());

				TextView ad = (TextView) listItem.findViewById(R.id.browse_app_description);
				String shortDest = apps.get(position).getDescription();
				if(shortDest.length()>40) {
					shortDest = shortDest.substring(0, 41);
					shortDest = shortDest.substring(shortDest.lastIndexOf(" "));
				}
				ad.setText(shortDest);

				ImageView ap = (ImageView) listItem.findViewById(R.id.browse_app_pic);
				//ap.setText(apps.get(position).etd);

				return listItem;
			}

			public void expandApp(View v) {
				LinearLayout l = (LinearLayout) v;
				Fragment downloadAppFragment = new DownloadApp(); 
				((ReviewerMain)getActivity()).lastViewed = (String)((TextView)((LinearLayout)l.getChildAt(1)).getChildAt(0)).getText();
				android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction(); 
				transaction.replace(R.id.reviewer_container,downloadAppFragment); // the container in Main page and the fragment so it starts the fragment in the container
				transaction.commit();
			}
		}
	}
	public static class DownloadApp extends Fragment{
		private ListView lv;
		private TextView nm, des;
		private App curApp;

		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public DownloadApp() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// Create a new TextView and set its text to the fragment's section
			// number argument value.

			return inflater.inflate(R.layout.app_download, container, false);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {

			super.onActivityCreated(savedInstanceState);

			for(App a: applist) {
				if(a.getName().equals(lastViewed)){
					curApp = a;
					break;
				}
			}

			nm = (TextView)getView().findViewById(R.id.app_download_title);
			nm.setText(curApp.getName());
			des = (TextView)getView().findViewById(R.id.app_download_description);
			des.setText(curApp.getDescription());

			//Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			lv = (ListView)getView().findViewById(R.id.app_download_review_on);

			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.app_download_adapter_item, curApp.getDeveloperAsksFor());
			lv.setAdapter(adapter);
		}

		public void downloadApp(View v) {
			curApp.addReviewer(curUser.getUsername());
		}
	}


	public static class WriteReview extends Fragment{
		private Spinner apps;
		private App curApp;
		private ArrayList<App> appsToReview;
		private ArrayAdapter<String> adapter;
		private LinearLayout checkboxes;
		private Review review;
		private EditText et;
		private RatingBar rb;
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public WriteReview() {
			review = new Review();
			review.setReviewer(curUser);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// Create a new TextView and set its text to the fragment's section
			// number argument value.

			return inflater.inflate(R.layout.write_review, container, false);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {

			super.onActivityCreated(savedInstanceState);

			appsToReview = new ArrayList<App>();

			for(App a: applist) {
				if(a.getReviewers().contains(curUser.getUsername())){
					appsToReview.add(a);
				}
			}

			ArrayList<String> appNames = new ArrayList<String>();
			for(App a:appsToReview) {
				appNames.add(a.getName());
			}

			//Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			apps = (Spinner)getView().findViewById(R.id.review_spinner);

			adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item,appNames);

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			apps.setAdapter(adapter);
			apps.setOnItemSelectedListener(new fSpinnerSelectedListener());  
			apps.setVisibility(View.VISIBLE);  
			
			checkboxes = (LinearLayout)getView().findViewById(R.id.review_checkboxes);
			String[] af = curApp.getDeveloperAsksFor();
			for(int i = 0; i<af.length; i++) {
				review.addReviewOn(af[i]);
				CheckBox ch = new CheckBox(getActivity());
				ch.setText(af[i]);
				ch.setTag(af[i]);
				ch.setChecked(true);
				checkboxes.addView(ch);
				ch.setOnClickListener(new OnClickListener() {
					  @Override
					  public void onClick(View v) {
						if (((CheckBox) v).isChecked()) {
							review.addReviewOn((String)(((CheckBox)v).getTag()));
						}else{
							review.removeReviewOn((String)(((CheckBox)v).getTag()));
						}
					  }
					});
			}
			et = (EditText)getView().findViewById(R.id.review_editText);
			rb = (RatingBar)getView().findViewById(R.id.review_ratingBar);
		}

		public void submitReview(View v) {
			review.setReviewText(et.getText().toString());
			review.setRating(rb.getRating());
			review.setSubmitTime(new Date());
			curApp.addReviewList(review);
			//TODO: push review onto shared pref.
		}

		class fSpinnerSelectedListener implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				curApp = (App)appsToReview.get(pos);
			}
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		}
	}
	public static class Notification extends Fragment{
		private ArrayList<Transaction> tl;
		private ListView lv;

		public Notification() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// Create a new TextView and set its text to the fragment's section
			// number argument value.

			return inflater.inflate(R.layout.notification, container, false);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {

			super.onActivityCreated(savedInstanceState);
			Transaction[] trlist = curUser.getTransactionList();
			for(int i = 0; i<trlist.length; i++) {
				tl.add(trlist.length-1-i, trlist[i]);
			}
			
			lv = (ListView)getView().findViewById(R.id.notification_lv);

			NotificationListAdapter adapter = new NotificationListAdapter(getActivity(),tl);
			lv.setAdapter(adapter);
		}
		
		
		class NotificationListAdapter extends ArrayAdapter<Transaction> {
			private final Context context;
			private final ArrayList<Transaction> values;

			public NotificationListAdapter(Context context,
					ArrayList<Transaction> values) {
				super(context, R.layout.notification_adapter_item, values);
				this.context = context;
				this.values = values;
			}

			// this method is called once for each item in the list
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View listItem = inflater.inflate(R.layout.notification_adapter_item,
						parent, false);

				TextView tv = (TextView) listItem.findViewById(R.id.notification_tv);
				String msg = "You have received $"+ tl.get(position).getAmount()
						+" for your review of "+ tl.get(position).getAppName()+"! Thank you.";
				tv.setText(msg);
				if(tl.get(position).getRead()) {
					tv.setBackgroundResource(R.drawable.rounded_gray);
				}else{
					tv.setBackgroundResource(R.drawable.rounded_blue);
					tl.get(position).setRead(true);
				}
				return listItem;
			}

		}
		

	}

}
