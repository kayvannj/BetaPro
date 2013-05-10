package com.androidians.betapro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class PasswordReset extends Activity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.forget_password);
		
		Button send = (Button)findViewById(R.id.sendEmail);
		TextView contact =(TextView)findViewById(R.id.contact_text);
		contact.setClickable(true);
		contact.setTextColor(Color.parseColor("#00bfff"));
		
		
		TextView login=(TextView)findViewById(R.id.login_text);
		login.setClickable(true);
		final Intent loginIntent = new Intent(this,Login.class);
		login.setTextColor(Color.parseColor("#00bfff"));
	    login.setOnClickListener(new View.OnClickListener() {
	   		
	   		@Override
	   		public void onClick(View v) {
	   			// TODO Auto-generated method stub
	   			
	   			
	   			startActivity(loginIntent);
	   			
	   		}
	   	});
		
		
		
		send.setOnClickListener(new View.OnClickListener() {
	   		
	   		@Override
	   		public void onClick(View v) {
	   			// TODO Auto-generated method stub
	   			
	   			
	   			EditText email=(EditText)findViewById(R.id.inputEmail);
	   			if(email.getText().toString().equals("")){
	   				email.setError("Email cannot be empty");
	   				
	   				
	   			    return;	
	   			}
	   			//Todo: send an email containing login info to the email address
	   			Toast.makeText(getApplicationContext(), "Email sent!", Toast.LENGTH_LONG).show();	   			
	   			
	   		}
	   	});
		
		
		
		login.setOnClickListener(new View.OnClickListener() {
	   		
	   		@Override
	   		public void onClick(View v) {
	   			// TODO Auto-generated method stub
	   			
	   			startActivity(loginIntent);
	   			
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

