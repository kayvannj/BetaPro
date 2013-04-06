package com.androidians.betapro;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		User testDeveloper = new User("Kayvan", "123456");
		User testReviewer  = new User("Alice", "654321");
		App testApp = new App("App1", "App1ID", "this is app1 description", "icon1", "apk1", 9.3, 1, 10, 20);
		testApp.addScreenShots("Screen Shot 1");
		testApp.addScreenShots("Screen Shot 2");
		testApp.addDeveloperAsksFor("Performance");
		testApp.addDeveloperAsksFor("UI");
		
		testDeveloper.addApp(testApp);
		
		ArrayList<String> developerAsksFor = new ArrayList<String>();
		developerAsksFor.add("UI");
		developerAsksFor.add("Performance");
		
		
		Log.d("test user",testDeveloper.toString());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
