package com.androidians.betapro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAppsListAdapter extends BaseExpandableListAdapter{
	private final Context context;
	private final ArrayList<App> appsList;
	
	private ArrayList<TextView> textList;
	TextView tv;
	ListView list;
	
	int index;
	View listItem;
	String username;
	
	public MyAppsListAdapter(Context context,ArrayList<App> appsList,String username){
		
		this.context=context;
		this.appsList=appsList;
		this.username = username;
		textList=new ArrayList<TextView>();
		
	}
	



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

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		
		TextView tv = (TextView) convertView.findViewById(R.id.childItem);
		TextView detailTv = (TextView) convertView.findViewById(R.id.childDetailItem);

		ArrayList<Review> reviewList = Review.getReviewFromReviewString(Login.getSharedPreferences(context), this.appsList.get(groupPosition).getReviewListString());
		if (reviewList.size()>0) {
			tv.setText(reviewList.get(childPosition).getReviewText());
			Date tempdate = new Date(reviewList.get(childPosition).getSubmitTime());
			detailTv.setText("Reviewed by "+reviewList.get(childPosition).getReviewer().substring(0, reviewList.get(childPosition).getReviewer().length()-6)+" on "+dateFormat.format(tempdate));
		}
		
		return convertView;
	}
	

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if (appsList.get(groupPosition).getReviewList().size()==0){
			Toast.makeText(context, "This app has no reviews yet", Toast.LENGTH_SHORT).show();
		}
		return this.appsList.get(groupPosition).getReviewList().size();
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
		 final RatingBar rbar = (RatingBar) convertView.findViewById(R.id.ratingBar); //star bar
		 
		 ArrayList<Review> reviewList = Review.getReviewFromReviewString(Login.getSharedPreferences(context), this.appsList.get(groupPosition).getReviewListString());
		 
		 double sum =0;
		 
		 for (int i=0;i<reviewList.size();i++){
			 sum+= reviewList.get(i).getRating();
		 }
		 rbar.setRating((float) (sum/reviewList.size()));
		 tv.setText(this.appsList.get(groupPosition).getName());
		 ctv.setText(this.appsList.get(groupPosition).getReviewList().size()+" reviews");
		 
		 
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
