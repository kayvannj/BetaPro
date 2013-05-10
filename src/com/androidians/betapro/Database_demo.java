package com.androidians.betapro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.net.ParseException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Database_demo extends ListActivity {
	String WEB = "http://androidians.comuf.com/data.php/?func=1";
	String LOCAL = "http://192.168.1.3/?func=1";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		String result = null;
		InputStream is = null;
		StringBuilder sb = null;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
			}
}