package com.androidians.betapro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Register extends Activity {

	public final static String USERS_KEY = "users";
	public final static String CURRENT_USER = "curUser";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		setContentView(R.layout.signup);
		
		EditText name = (EditText) findViewById(R.id.inputname);
		
		final SharedPreferences preferences = getSharedPreferences(this);
		final SharedPreferences.Editor editor = preferences.edit();
		
		
		HashSet<String> emptySet = new HashSet<String>();
		final HashSet<String> allIDs = (HashSet<String>) preferences.getStringSet(USERS_KEY, emptySet);
		
		final ArrayList<User> allUsers = new ArrayList<User>();
		if (!name.getText().toString().equals("")) {
			Iterator itr = allIDs.iterator();
			
			//itr.next();
			while(itr.hasNext()) {
				String userInfo = preferences.getString((String) itr.next(), "");
				allUsers.add(new User(userInfo));
			}
		}	
		
		//Intent to start reading the documentation or proceed to log in
		final Intent helpIntent = new Intent(this,Help.class);
		
		Button reg =(Button)findViewById(R.id.regBtn);
        reg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				//Field validators
				EditText name = (EditText) findViewById(R.id.inputname);
				if (name.getText().toString().equals("")){
					
					name.setError("Please choose a user name");
					return;
				}
				
				EditText email = (EditText)findViewById(R.id.inputemail);
				if(email.getText().toString().equals("")){
					email.setError("email is required so you can get your password back");
					return;
				}

				
				EditText pass = (EditText) findViewById(R.id.inputpassword);
				if(pass.getText().toString().equals("")){
					pass.setError("Please enter a password");
					return;
				}
				
				
				if(pass.getText().toString().length()<6){
					pass.setError("password must be at least 6 charactors long");
					return;
					
				}
				
				EditText repeat =(EditText)findViewById(R.id.inputrepeatpassword);
				if (!pass.getText().toString().equals(repeat.getText().toString())){
					repeat.setError("confirmation password does not match");
					return;
				}
				
				
				
				//These are old toast validations. 
				/*if (name.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Please choose a username",Toast.LENGTH_LONG).show();
					return;
				}
				if(email.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Please enter an email", Toast.LENGTH_LONG).show();
					return;
				}
				if(pass.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Please choose a password",Toast.LENGTH_LONG).show();
					return;
				}
				
				if(pass.getText().toString().length()<6){
					Toast.makeText(getApplicationContext(), "Password must be at least 6 charachers long", Toast.LENGTH_LONG).show();
					//return;
				}
				
				if (!pass.getText().toString().equals(repeat.getText().toString())){
				Toast.makeText(getApplicationContext(), "Passwords does not match",Toast.LENGTH_LONG).show();
					return;
				}*/
				
			
				
				
				
				
				
				
				
				
				
				//if all validations passed, create a new user and start helpIntent
				User newUser = new User(name.getText().toString(), pass.getText().toString(),name.getText().toString()+"UserID");
			        	allUsers.add(newUser);
			        	editor.putString(newUser.getUserID(), newUser.toString());
			        	allIDs.add(newUser.getUserID());
			        	editor.putStringSet(USERS_KEY, allIDs);
			        	editor.commit();
			   startActivity(helpIntent);
				
		        
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
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            
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
	protected void onDestroy() {
	    //mMixpanel.flush();
		super.onDestroy();
	}

}

