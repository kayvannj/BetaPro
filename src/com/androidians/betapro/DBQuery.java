package com.androidians.betapro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
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

import android.net.ParseException;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class DBQuery {
	static String WEB = "http://androidians.comuf.com/data.php/?func=1";
	static String LOCAL = "http://192.168.1.3/?func=1";
	static String result = null;
	static InputStream is = null;
	static StringBuilder sb = null;
	static ArrayList<NameValuePair> nameValuePairs;
	
	/**
	 * query("users",["username","kayvan"],"")
	 * @param table
	 * @param cond
	 * @param args
	 * @return
	 */
	public static Hashtable<String, String> query(String table,String[] cond, String ... args){
		Hashtable<String, String> resTable = new Hashtable<String, String>(); 
		final String q = "SELECT  `"+table+"` . * FROM  `"+table+"`	WHERE (`"+table+"`.`"+cond[0]+"` ="+cond[1]+") ORDER BY `"+table+"`.`id` DESC";
		
		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new NameValuePair() {
					
					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return q;
					}
					
					@Override
					public String getName() {
						// TODO Auto-generated method stub
						return "q";
					}
				});
		
				
		List<String> r = new ArrayList<String>();

		try {
			// http post
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(WEB);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			resTable.put("ERROR", e.toString());
		}
		// Convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"));

			sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
		} catch (Exception e) {
			resTable.put("ERROR", e.toString());		}
		// END Convert response to string
		try {
			JSONArray jArray = new JSONArray(result);
			JSONObject json_data = null;
			for (int i = 0; i < jArray.length(); i++) {
				json_data = jArray.getJSONObject(i);
				
				for (String arg : args) {
					//resTable.put(key, json_data.getString(arg));
				}
				
				//r.add();
			}

		} catch (JSONException e1) {
			resTable.put("ERROR", e1.toString());
		} catch (ParseException e1) {
			resTable.put("ERROR", e1.toString());
		}

		
		return new Hashtable<String, String>();
	}
	
}
