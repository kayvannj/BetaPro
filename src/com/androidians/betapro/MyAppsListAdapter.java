package com.androidians.betapro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MyAppsListAdapter extends BaseExpandableListAdapter{
	private final Context context;
	private final ArrayList<App> appsList;
	
	private ArrayList<TextView> textList;
	TextView tv;
	ListView list;
	
	int index;
	View listItem;
	
	public MyAppsListAdapter(Context context,ArrayList<App> appsList){
		
		this.context=context;
		this.appsList=appsList;
		textList=new ArrayList<TextView>();
				
		
	}
	
	/*public void setListView(final ListView list){
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
		
	}*/

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return this.appsList.get(groupPosition).getDescription();
		
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView==null){
			LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.expandable_child, null);
		}
			
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		TextView tv = (TextView) convertView.findViewById(R.id.childItem);
		TextView detailTv = (TextView) convertView.findViewById(R.id.childDetailItem);
		
		
		
		
		ArrayList<Review> reviewList = Review.getReviewFromReviewString(Login.getSharedPreferences(context), this.appsList.get(groupPosition).getReviewListString());
		if (reviewList.size()>0) {
			tv.setText(reviewList.get(childPosition).getReviewText());
			detailTv.setText("Reviewed by "+reviewList.get(childPosition).getReviewer()+" on "+dateFormat.format(reviewList.get(childPosition).getSubmitTime()));
		}
		
		return convertView;
	}
	

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return this.appsList.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return this.appsList.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public View getGroupView(final int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 if (convertView==null){
			 LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			 convertView= inflater.inflate(R.layout.expandable_list_headings, null);
			 
		 }
		 final TextView tv = (TextView) convertView.findViewById(R.id.heading);
		 final TextView ctv = (TextView) convertView.findViewById(R.id.numReviewers);
		 tv.setText(this.appsList.get(groupPosition).getName());
		 ctv.setText(this.appsList.get(groupPosition).getReviewCounter()+" reviews");
		 
		 
		 //RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar);
		 /*ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener(){

			@Override
			public void onRatingChanged(RatingBar arg0, float rating, boolean arg2) {
				
				appsList.get(groupPosition).setAppRate(rating);
				tv.setText("rated"+rating);
				// TODO Auto-generated method stub
				
			}
			 
		 });*/
		 return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}
}
