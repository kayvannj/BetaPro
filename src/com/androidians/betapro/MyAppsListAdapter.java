package com.androidians.betapro;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MyAppsListAdapter extends ArrayAdapter<App> implements OnClickListener{
	private final Context context;
	private final ArrayList<App> appsList;
	
	private ArrayList<TextView> textList;
	TextView tv;
	ListView list;
	
	int index;
	View listItem;
	
	public MyAppsListAdapter(Context context,ArrayList<App> appsList){
		super(context,R.layout.app_item,appsList);
		this.context=context;
		this.appsList=appsList;
		textList=new ArrayList<TextView>();
				
		
	}
	
	public void setListView(final ListView list){
		this.list=list;
		
		
	}
    public int getCount() {
        // TODO Auto-generated method stub
		
        return this.appsList.size();
    }
	
	@Override
	  public App getItem(int arg0) {
	        // TODO Auto-generated method stub
		Toast.makeText(getContext(), "getItem"+arg0, Toast.LENGTH_LONG).show();
		 this.index=arg0;
	      return this.appsList.get(arg0);
	    }
	
	@Override
	  public long getItemId(int arg0) {
	        // TODO Auto-generated method stub
		 
	     return arg0;
	    }
	@Override
	public View getView(int position, View convertView,ViewGroup parent){
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listItem = inflater.inflate(R.layout.app_item, parent,false);
		tv = (TextView) listItem.findViewById(R.id.AppName);
		tv.setOnClickListener(this);
		
		
		
		tv.setText(this.appsList.get(position).getName());
		textList.add(tv);
		return listItem;
		
		
		}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final int position = list.getPositionForView((View) v.getParent());
		textList.get(position).setText("clicked"+position);
        Toast.makeText(getContext(), position+"", Toast.LENGTH_LONG).show();
		
	}
}
