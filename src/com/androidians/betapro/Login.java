package com.androidians.betapro;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class Login extends Activity {

	public final static String USERS_KEY = "users";
	public final static String CURRENT_USER = "curUser";
	//MixpanelAPI mMixpanel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//mMixpanel = MixpanelAPI.getInstance(this, "YOUR_API_TOKEN");
		
		setContentView(R.layout.activity_login);
		
		//Testing Data
/*		User testDeveloper = new User("Kayvan", "123456","KayvanUserID");
		User testReviewer  = new User("Alice", "654321","AliceUserID");
		User testReviewer2 = new User("Mia", "blahblah","MiaUserID");
		
		App testApp = new App("App1", "App1ID", "this is app1 description", "icon1", "apk1", 9.3, 1, 10, 20);
		
		testApp.addScreenShots("Screen Shot 1");
		testApp.addScreenShots("Screen Shot 2");
		testApp.addDeveloperAsksFor("Performance");
		testApp.addDeveloperAsksFor("UI");
		testApp.addReviewer("MiaUserID");
		ArrayList<String> a = new ArrayList<String>();
		a.add("Heuristic1");
		a.add("Heuristic2");
		
		Review r = new Review("MiaUserID","Review1ID", a, new Date(), "amazing", 4.0);
		testApp.addReviewList(r.getReviewID());
		
		Transaction t = new Transaction("transaction1ID","KayvanUserID", "MiaUserID", new Date(), 5.0, "App1", 37, false);
		testReviewer2.addTransaction(t.getTransactionID());
		Transaction t2 = new Transaction("transaction2ID","JamesUserID", "MiaUserID", new Date(), 2.0, "App2", 52, false);
		testReviewer2.addTransaction(t2.getTransactionID());
		
		testDeveloper.addApp(testApp.getappID());*/
		//System.out.println("test user: " + testDeveloper.toString());	
		
		final Intent intent = new Intent(this, Home.class);
		final EditText user = (EditText)findViewById(R.id.editText1);
		final EditText pass = (EditText)findViewById(R.id.editText2);
		
		//setup shared prefrences
		final SharedPreferences preferences = getSharedPreferences(this);
		final SharedPreferences.Editor editor = preferences.edit();
		
		
		HashSet<String> emptySet = new HashSet<String>();
		final HashSet<String> allIDs = (HashSet<String>) preferences.getStringSet(USERS_KEY, emptySet);
		
/*		if(preferences.getString(testDeveloper.getUserID(), "f").equals("f")){
			allIDs.add(testDeveloper.getUserID());
			editor.putString(testDeveloper.getUserID(), testDeveloper.toString());
			editor.putStringSet(USERS_KEY, allIDs);
			editor.commit();
		}
		if(preferences.getString(testReviewer2.getUserID(), "f").equals("f")){
			allIDs.add(testReviewer2.getUserID());
			editor.putString(testReviewer2.getUserID(), testReviewer2.toString());
			editor.putStringSet(USERS_KEY, allIDs);
			editor.commit();
		}*/
		final ArrayList<User> allUsers = new ArrayList<User>();
		if (!user.getText().toString().equals("")) {
			Iterator itr = allIDs.iterator();
			
			//itr.next();
			while(itr.hasNext()) {
				//System.out.println("itr" + (String)itr.next());
				String userInfo = preferences.getString((String) itr.next(), "");
				allUsers.add(new User(userInfo));
			}
		}	
		
//		String userString = testDeveloper.toString();
//		System.out.println("checkpoint2");
//		System.out.println(userString);
		
		
		Button loginButton = (Button)findViewById(R.id.button1);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: Check for Password.
				if (!user.getText().toString().equals("")) {
					String userString = preferences.getString(user.getText().toString()+"UserID", "f");
					User activeUser;
			        if(userString != "f") {
			        	activeUser = new User(userString);
		            	//User activeUser = new User(user.getText().toString(), pass.getText().toString());
		            	//activeUser.fillUser(userString);
			        }else{
			        	activeUser = new User(user.getText().toString(), pass.getText().toString(),user.getText().toString()+"UserID");
			        	allUsers.add(activeUser);
			        	System.out.println("activeUser's tostring "+ activeUser.toString());
			        	editor.putString(activeUser.getUserID(), activeUser.toString());
			        	allIDs.add(activeUser.getUserID());
			        	editor.putStringSet(USERS_KEY, allIDs);
			        	editor.commit();
			        }
			        intent.putExtra(CURRENT_USER, activeUser.getUserID());
			        
		        	startActivity(intent);
				}
		        
			}
		});
	}

	public static SharedPreferences getSharedPreferences(Context con){
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(con);
		return preferences;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	
	@Override
	protected void onDestroy() {
	    //mMixpanel.flush();
		super.onDestroy();
	}

}

