package com.androidians.betapro;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MyAppsListAdapter extends ArrayAdapter<App>{
	private final Context context;
	private final ArrayList<App> appsList;
	
	public MyAppsListAdapter(Context context,ArrayList<App> appsList){
		super(context,R.layout.app_item,appsList);
		this.context=context;
		this.appsList=appsList;
		
		
	}
    public int getCount() {
        // TODO Auto-generated method stub
		
        return this.appsList.size();
    }
	
	@Override
	  public App getItem(int arg0) {
	        // TODO Auto-generated method stub
		 
	      return this.appsList.get(arg0);
	    }
	
	@Override
	  public long getItemId(int arg0) {
	        // TODO Auto-generated method stub
		 
	     return arg0;
	    }
	@Override
	public View getView(int positon, View convertView,ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View listItem = inflater.inflate(R.layout.app_item, parent,false);
		TextView tv = (TextView) listItem.findViewById(R.id.AppName);
		tv.setText(this.appsList.get(positon).getName());
		
		return listItem;
		
		
		}


}
