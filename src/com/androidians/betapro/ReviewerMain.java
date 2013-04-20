//Written by Juntao Mao

package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import android.widget.Toast;

public class ReviewerMain extends FragmentActivity implements
ActionBar.TabListener  {
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "reviewer_main_selected_navigation_item";
	private static final int TAB_BROWSE = 0;
	private static final int TAB_WRITE =1;
	private static final int TAB_NOTIFICATION = 2;
	private static ArrayList<App> applist;
	private static ArrayList<User> allUsers;
	private static User curUser;
	private static SharedPreferences preferences; 
	private static SharedPreferences.Editor editor;
	static String lastViewed;


	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		//set the layout
		setContentView(R.layout.reviewer_contain_layout);
		
		//setup of Shared Pref
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor = preferences.edit();
		
		//reading all user informations
		HashSet<String> emptySet = new HashSet<String>();
		HashSet<String> allIDs = (HashSet<String>) preferences.getStringSet(Login.USERS_KEY, emptySet);
		Iterator<String> itr = allIDs.iterator();
		allUsers = new ArrayList<User>();

		while(itr.hasNext()) {
			String userInfo = preferences.getString(itr.next().trim(), "Error User");
			if(!userInfo.equals(""))
				allUsers.add(new User(userInfo));
		}

		applist = new ArrayList<App>();
		for(User u: allUsers) {
			applist.addAll(App.getAppsFromAppString(preferences, u.getAppListString()));
		}
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		String curUserName = i.getStringExtra((Login.CURRENT_USER));
		for(User u: allUsers) {
			if(u.getUserID().equals(curUserName)) {
				curUser = u;
				break;
			}
		}
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
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, Home.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            intent.putExtra(Login.CURRENT_USER, curUser.getUserID());
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
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
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_BROWSE){ //check to see which tab has been selected
			Fragment browseFragment = new BrowseApps(); 
			transaction.replace(R.id.reviewer_container, browseFragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
		}
		if(tab.getPosition()==TAB_WRITE){ //check to see which tab has been selected
			Fragment writeReviewFragment = new WriteReview();
			transaction.replace(R.id.reviewer_container,writeReviewFragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
		}
		if(tab.getPosition()==TAB_NOTIFICATION){ //check to see which tab has been selected
			Fragment notificationFragment = new Notification();
			transaction.replace(R.id.reviewer_container,notificationFragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if(tab.getPosition()==TAB_NOTIFICATION){
			ArrayList<Transaction> tlist = Transaction.getTransactionFromTransactionString(preferences, curUser.getTransactionListString());
			for(Transaction t:tlist) {
				t.setRead(true);
			}
		
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public ArrayList<App> getAppList(){
		System.out.println("GetAppList: " + applist.toString());
		return applist;
	}

	public void expandApp(View v) {
		LinearLayout l = (LinearLayout) v;
		Fragment downloadAppFragment = new DownloadApp(); 

		lastViewed =((TextView)l.findViewById(R.id.browse_app_title)).getText().toString();
		android.support.v4.app.FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction(); 
		transaction.replace(R.id.reviewer_container,downloadAppFragment); // the container in Main page and the fragment so it starts the fragment in the container
		transaction.commit();
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
				if(apps.get(position).getName()!=null){
					an.setText(apps.get(position).getName());

					TextView ad = (TextView) listItem.findViewById(R.id.browse_app_description);
					String shortDest = apps.get(position).getDescription();
					if(shortDest.length()>40) {
						shortDest = shortDest.substring(0, 41);
						shortDest = shortDest.substring(shortDest.lastIndexOf(" "));
					}
					ad.setText(shortDest);

					ImageView ap = (ImageView) listItem.findViewById(R.id.browse_app_pic);
					//TODO: set image with uri?
					//ap.setText(apps.get(position).etd);
				}
				return listItem;
			}
		}
	}


	public static class DownloadApp extends Fragment{
		private ListView lv;
		private TextView nm, des;
		private App curApp;
		private Button downloadButton;

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

			ArrayAdapter<String> adapter = new DownloadAppListAdapter(getActivity(), curApp.getDeveloperAsksFor());
			lv.setAdapter(adapter);

			downloadButton = (Button)getView().findViewById(R.id.app_download_button);
			downloadButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					System.out.println(curApp.toString());
					System.out.println(curUser.toString());
					if(!curApp.getReviewers().contains(curApp.getName())){
						curApp.addReviewer(curUser.getUserID());
					}
					System.out.println("Checkpoint!");
					Toast.makeText(getActivity(), "App has been downloaded, have fun testing!",Toast.LENGTH_LONG).show();
				}
			});
		}

		class DownloadAppListAdapter extends ArrayAdapter<String> {
			private final Context context;
			private final ArrayList<String> values;

			public DownloadAppListAdapter(Context context,
					ArrayList<String> values) {
				super(context, R.layout.app_download_adapter_item, values);
				this.context = context;
				this.values = values;
			}

			// this method is called once for each item in the list
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View listItem = inflater.inflate(R.layout.app_download_adapter_item,
						parent, false);

				CheckBox rb = (CheckBox) listItem.findViewById(R.id.review_item);
				rb.setText(values.get(position));
				return listItem;
			}
		}


	}


	public static class WriteReview extends Fragment{
		private Spinner apps;
		private App curApp;
		private ArrayList<App> appsToReview;
		private ArrayAdapter<String> adapter;
		private ArrayList<String> appNames;
		private LinearLayout checkboxes;
		private Review review;
		private EditText et;
		private RatingBar rb;
		private Button submitButton;
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public WriteReview() {
			review = new Review();
			System.out.println("curUser: " + curUser.toString());
			review.setReviewer(curUser.getUserID());//Necessary to create unique reviewID 
			
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
				if(a.getReviewers().contains(curUser.getUserID())){
					appsToReview.add(a);
				}
			}
			System.out.println("WriteReview: onCreate");
			appNames = new ArrayList<String>();
			if(appsToReview.size()==0) {
				appNames.add("Please download an app from the Browse tab to try first!");
			}else{
				for(App a:appsToReview) {
					appNames.add(a.getName());
				}
			}

			//Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			apps = (Spinner)getView().findViewById(R.id.review_spinner);

			adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_spinner_item,appNames);

			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			apps.setAdapter(adapter);
			apps.setOnItemSelectedListener(new fSpinnerSelectedListener());  
			apps.setVisibility(View.VISIBLE);  


			et = (EditText)getView().findViewById(R.id.review_editText);
			rb = (RatingBar)getView().findViewById(R.id.review_ratingBar);

			submitButton = (Button)getView().findViewById(R.id.review_submitButton);
			submitButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					review.setReviewText(et.getText().toString());
					review.setRating(rb.getRating());
					review.setSubmitTime(new Date()); //Necessary to create unique reviewID 
					curApp.addReviewList(review.getReviewID());
					for(User u: allUsers) {
						for(App a: App.getAppsFromAppString(preferences, u.getAppListString())) {
							if(a.getAppID().equals(curApp.getAppID())){
								a.addReviewList(review.getReviewID());
								a.addReviewer(curUser.getUserID());
								editor.putString(u.getUserID(), u.toString());
								editor.putString(review.getReviewID(), review.toString());
								editor.commit();
								break;
								//How many breaks?
							}
						}
					}
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
						      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
					Toast.makeText(getActivity(), "Review has been submitted, thank you!",Toast.LENGTH_LONG).show();
					System.out.println(review.toString());
//					android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//					Fragment browseFragment = new BrowseApps(); 
//					transaction.replace(R.id.reviewer_container, browseFragment); // the container in Main page and the fragment so it starts the fragment in the container
//					transaction.commit();
					ActionBar actionBar = getActivity().getActionBar();
					actionBar.setSelectedNavigationItem(TAB_BROWSE);
				}
			});

		}

		class fSpinnerSelectedListener implements OnItemSelectedListener {
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				if(!appNames.get(0).equals("Please download an app from the Browse tab to try first!")){
					curApp = (App)appsToReview.get(pos);
					checkboxes = (LinearLayout)getView().findViewById(R.id.review_checkboxes);
					ArrayList<String> af = curApp.getDeveloperAsksFor();
					for(int i = 0; i<af.size(); i++) {
						review.addReviewOn(af.get(i));
						CheckBox ch = new CheckBox(getActivity());
						ch.setText(af.get(i));
						ch.setTag(af.get(i));
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
				}
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
			ArrayList<Transaction> trlist = Transaction.getTransactionFromTransactionString(preferences, curUser.getTransactionListString());
			System.out.println("cur transaction listL " + trlist.toString());
			tl = new ArrayList<Transaction>();
			for(int i = 0; i<trlist.size(); i++) {
				//tl.add(trlist.size()-1-i, trlist.get(i));
				tl.add(trlist.get(i));
				System.out.println(trlist.get(i).getRead());
			}

			if(tl.size()==0){
				TextView noNoti = (TextView)getView().findViewById(R.id.notification_none);
				noNoti.setVisibility(View.VISIBLE);
			}else{
				lv = (ListView)getView().findViewById(R.id.notification_lv);
				System.out.println("TRANSACTION LIST " + tl.toString());
				NotificationListAdapter adapter = new NotificationListAdapter(getActivity(),tl);
				lv.setAdapter(adapter);
			}
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
				//tv.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				if(tl.get(position).getRead()) {
					System.out.println("READ? " + tl.get(position).getRead());
//					tv.setBackgroundResource(R.drawable.rounded_gray);
					tv.setBackgroundColor(Color.GRAY);
				}else{
					//tv.setBackgroundResource(R.drawable.rounded_blue);
					tv.setBackgroundColor(Color.WHITE);
					//tl.get(position).setRead(true);
				}
				return listItem;
			}

		}


	}

}

