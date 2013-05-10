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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Help extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		

		setContentView(R.layout.help);
		
		final Intent loginIntent = new Intent(this,Login.class);
		final Intent docIntent = new Intent(this,doc.class);
      Button login = (Button)findViewById(R.id.button1);
      login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: Check for Password.
				
				startActivity(loginIntent);
				
		        
			}
		});
		
      Button doc=(Button)findViewById(R.id.button2);
      doc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: Check for Password.
				
				startActivity(docIntent);
				
		        
			}
		});
      
				
				
				
	
	
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

