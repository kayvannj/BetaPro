//Written by Juntao Mao

package com.androidians.betapro;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
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
	//private static final int TAB_WRITE =1;
	private static final int TAB_NOTIFICATION = 1;
	private static ArrayList<App> applist;
	private static ArrayList<User> allUsers;
	private static User curUser;
	private static App curApp;
	private static SharedPreferences preferences; 
	private static SharedPreferences.Editor editor;
	static String lastViewed;
	private static String WEB_ADDRESS = "http://192.168.1.3/BetaProFiles/"; 

	

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

		System.out.println("OnCreate1");
		applist = new ArrayList<App>();
		for(User u: allUsers) {
			applist.addAll(App.getAppsFromAppString(preferences, u.getAppListString()));
		}
		System.out.println("OnCreate2");
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		String curUserName = i.getStringExtra((Login.CURRENT_USER));
		for(User u: allUsers) {
			if(u.getUserID().equals(curUserName)) {
				curUser = u;
				break;
			}
		}
		System.out.println("OnCreate3");
		// Set up the action bar to show tabs and hide title.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(true);
		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.browse_tab)
				.setTabListener(this));
//		actionBar.addTab(actionBar.newTab().setText(R.string.writereview_tab)
//				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.notification_tab)
				.setTabListener(this));
		actionBar.setSelectedNavigationItem(TAB_BROWSE);
		System.out.println("OnCreate4");
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
	        case R.id.help:
	        	Intent helpIntent = new Intent(this,doc.class);
	        	startActivity(helpIntent);
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
//		if(tab.getPosition()==TAB_WRITE){ //check to see which tab has been selected
//			Fragment writeReviewFragment = new WriteReview();
//			transaction.replace(R.id.reviewer_container,writeReviewFragment); // the container in Main page and the fragment so it starts the fragment in the container
//			transaction.commit();
//		}
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
				t.setRead(true,preferences.edit());
				editor.putString(t.getTransactionID(),t.toString());
				editor.commit();
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
		TextView maxReward;
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
			maxReward = (TextView)getView().findViewById(R.id.browse_max_reward);
			lv = (ListView)getView().findViewById(R.id.browse_lv);
			//Arraylist of apps newest first
			ArrayList<App> appsFromNewest = ((ReviewerMain)getActivity()).getAppList();
			Collections.reverse(appsFromNewest);
			//Arraylist of apps downloaded by current user first, then 
			ArrayList<App> appsDownloadedFirst = new ArrayList<App>();
			for(int i = appsFromNewest.size()-1;i>=0; i--) {
				if(appsFromNewest.get(i).getReviewers().contains(curUser.getUserID())){
					appsDownloadedFirst.add(0, appsFromNewest.get(i));
				}
			}
			for(int i = 0; i<appsFromNewest.size(); i++){
				if(!appsFromNewest.get(i).getReviewers().contains(curUser.getUserID())){
					appsDownloadedFirst.add(appsFromNewest.get(i));
				}
			}
			AppListAdapter adapter = new AppListAdapter(getActivity(), appsDownloadedFirst); 
			lv.setAdapter(adapter);
			lv.setVisibility(View.VISIBLE);
		}

		class AppListAdapter extends ArrayAdapter<App> {
			private final Context context;
			private final ArrayList<App> apps;
			public AppListAdapter(Context context,
					ArrayList<App> values) {
				super(context, R.layout.browse_app_adapter_item, values);
				this.context = context;
				this.apps = values;
				System.out.println("ListAdapter apps: " + this.apps);
				if(apps.size()==0){
					maxReward.setVisibility(View.GONE);
				}else{
					maxReward.setVisibility(View.VISIBLE);
				}
			}

			// this method is called once for each item in the list
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				System.out.println("getView" + position);
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
						shortDest = shortDest.substring(0,shortDest.lastIndexOf(" "));
						shortDest = shortDest + "...";
					}
					ad.setText(shortDest);
					ad.setVisibility(View.VISIBLE);
					
					ImageView ap = (ImageView) listItem.findViewById(R.id.browse_app_pic);
					ap.setVisibility(View.VISIBLE);
					new ImageLoader(ap, WEB_ADDRESS+apps.get(position).getIcon().split("/")[apps.get(position).getIcon().split("/").length-1]);
					//ap.setText(apps.get(position).etd);
					TextView pay = (TextView)listItem.findViewById(R.id.browse_app_max_reward);
					pay.setText("$"+apps.get(position).getMaxPay());
					pay.setVisibility(View.VISIBLE);
				}
				return listItem;
			}
		}
	}


	public static class DownloadApp extends Fragment{
		private LinearLayout lv;
		private TextView nm, des, reward;
		private Button downloadButton, backButton, reviewButton;
		private ImageView appIcon;
		private HorizontalScrollView screenshots;
		private Dialog dialog;
		private String downloadedApk;
		
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

			appIcon = (ImageView)getView().findViewById(R.id.app_download_pic);
			new ImageLoader(appIcon, WEB_ADDRESS+curApp.getIcon().split("/")[curApp.getIcon().split("/").length-1]);
			
			//Button browseScreenShot= (Button)this.getView().findViewById(R.id.BrowseScreenShot);
			lv = (LinearLayout)getView().findViewById(R.id.app_download_review_on);
			//lv.setScrollContainer(false);
			LayoutInflater inflater = (LayoutInflater) getActivity()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			for(int j = 0; j < curApp.getDeveloperAsksFor().size(); j++ ) {
				View listItem = inflater.inflate(R.layout.app_download_adapter_item,
						null);
				TextView rb = (TextView) listItem.findViewById(R.id.review_item);
				rb.setText(curApp.getDeveloperAsksFor().get(j).trim());
				lv.addView(listItem);
			}
			
			screenshots = (HorizontalScrollView)getView().findViewById(R.id.app_download_screenshots);
			LinearLayout ll = new LinearLayout(getActivity());
			ll.setOrientation(LinearLayout.HORIZONTAL);
			
			for(int j = 0; j < curApp.getScreenShots().size(); j++) {
				ImageView singleScreenShot = new ImageView(this.getActivity());
				String path = curApp.getScreenShots().get(j);
				new ImageLoader(singleScreenShot, WEB_ADDRESS + path.split("/")[path.split("/").length-1]);
				singleScreenShot.setAdjustViewBounds(true);
				singleScreenShot.setPadding(5, 0, 5, 0);
				
				ll.addView(singleScreenShot);
			}
			screenshots.addView(ll);
			if(curApp.getScreenShots().size()==0)
				screenshots.setVisibility(View.GONE);
			else
				screenshots.setVisibility(View.VISIBLE);
			
			reward = (TextView)getView().findViewById(R.id.app_download_reward);
			reward.setText("$"+curApp.getMinPay()+"~$"+curApp.getMaxPay());
			
			dialog = new Dialog(getActivity());
			dialog.setContentView(R.layout.download_dialog);
			dialog.setTitle("Install App");
			dialog.setCancelable(true);
			
			
			downloadButton = (Button)getView().findViewById(R.id.app_download_button);
			downloadButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					if(!curApp.getReviewers().contains(curUser.getUserID())) {
						curApp.addReviewer(curUser.getUserID());
						editor.putString(curApp.getAppID(), curApp.toString());
						editor.commit();
					}
					//Toast.makeText(getActivity(), "App has been downloaded, have fun testing!",Toast.LENGTH_LONG).show();
					String apkName = curApp.getApk().split("/")[curApp.getApk().split("/").length-1];
					downloadedApk = ApkManager.downloadApk(getActivity(), apkName, WEB_ADDRESS+apkName);
					
					Button installNow = (Button) dialog.findViewById(R.id.download_dialog_install);
					Button installLater = (Button) dialog.findViewById(R.id.download_dialog_later);
					
					installNow.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							File application = new File(downloadedApk);
							ApkManager.installApk(getActivity(), Uri.fromFile(application));
						}
					});
					installLater.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.hide();
						}
					});
					dialog.show();
					//ApkManager.installApk(getActivity(), Uri.parse(downloadedApk));
				}
			});
			
			backButton = (Button)getView().findViewById(R.id.app_download_back);
			backButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					Fragment BrowseAppsFragment = new BrowseApps();
					FragmentManager fm = getActivity().getSupportFragmentManager();
					android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
					transaction.replace(R.id.reviewer_container,BrowseAppsFragment);
					transaction.commit();
				}
			});
			reviewButton = (Button)getView().findViewById(R.id.app_review_button);
			reviewButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Fragment ReviewAppsFragment = new WriteReview();
					FragmentManager fm = getActivity().getSupportFragmentManager();
					android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
					transaction.replace(R.id.reviewer_container,ReviewAppsFragment);
					transaction.commit();
				}
			});
		}



	}


	public static class WriteReview extends Fragment{
		private Spinner apps;
		private ArrayAdapter<String> adapter;
		private ArrayList<String> appNames;
		private LinearLayout checkboxes;
		private Review review;
		private EditText et;
		private RatingBar rb;
		private Button submitButton, backButton;
		private TextView appTitle;
		
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
			//appsToReview = new ArrayList<App>();

			appTitle = (TextView)getView().findViewById(R.id.review_app_title);
			appTitle.setText(curApp.getName());

			et = (EditText)getView().findViewById(R.id.review_editText);
			rb = (RatingBar)getView().findViewById(R.id.review_ratingBar);

			checkboxes = (LinearLayout)getView().findViewById(R.id.review_checkboxes);
			checkboxes.removeAllViews();
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
			
			submitButton = (Button)getView().findViewById(R.id.review_submitButton);
			submitButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
					review.setReviewText(et.getText().toString());
					review.setRating(rb.getRating());
					review.setSubmitTime(System.currentTimeMillis()); //Necessary to create unique reviewID 
					review.setReviewID(curUser.getUserID()+review.getSubmitTime());

					for(User u: allUsers) {
						for(App a: App.getAppsFromAppString(preferences, u.getAppListString())) {
							if(a.getAppID().equals(curApp.getAppID())){
								a.addReviewList(review.getReviewID());
								a.addReviewer(curUser.getUserID());
								editor.putString(u.getUserID(), u.toString());
								editor.putString(a.getAppID(),a.toString());
								editor.putString(review.getReviewID(), review.toString());
								editor.commit();
								break;
							}
						}
					}
					InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
						      Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
					Toast.makeText(getActivity(), "Review has been submitted, thank you!",Toast.LENGTH_LONG).show();
					System.out.println(review.toString());
					android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
					Fragment browseFragment = new BrowseApps(); 
					transaction.replace(R.id.reviewer_container, browseFragment); // the container in Main page and the fragment so it starts the fragment in the container
					transaction.commit();
				}
			});

			
			backButton = (Button)getView().findViewById(R.id.review_back);
			backButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					/*android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
					Fragment browseFragment = new BrowseApps(); 
				
					transaction.replace(R.id.reviewer_container, browseFragment); // the container in Main page and the fragment so it starts the fragment in the container
					transaction.commit();*/
					
					Fragment downloadAppFragment = new DownloadApp(); 

					
					//android.support.v4.app.FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
					android.support.v4.app.FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
					transaction.replace(R.id.reviewer_container,downloadAppFragment); // the container in Main page and the fragment so it starts the fragment in the container
					transaction.commit();
					
				}
			});
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
			for(int i = trlist.size()-1; i>=0; i--) {
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
					tv.setTypeface(null,  Typeface.NORMAL);
					//tv.setBackgroundColor(Color.GRAY);
				}else{
					//tv.setBackgroundColor(Color.WHITE);
					tv.setTypeface(null,  Typeface.BOLD);
					//tl.get(position).setRead(true,preferences.edit());
				}
				return listItem;
			}

		}


	}

}


