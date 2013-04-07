package com.androidians.betapro;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class DeveloperMain extends FragmentActivity implements ActionBar.TabListener {
	
	
	static MyAppsListAdapter appsListAdapter;
	static ListView myAppsListView;

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private static final int TAB_PUBLISH = 0;
	private static final int TAB_MYAPPS =1;
	private static FragmentManager fm;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		//set the layout
		setContentView(R.layout.developer_container_layout);
		// Set up the action bar to show tabs and hide title.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// For each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText(R.string.publish_tab)
				.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText(R.string.myapps_tab)// No action for this class yet
				.setTabListener(this));
		actionBar.setSelectedNavigationItem(TAB_PUBLISH);
		

		App testApp1= new App("test app 1", "", "", "","",	0, 0, 0,0); 
		App testApp2 = new App("test app 2", "", "", "","",	0, 0, 0,0); 
		ArrayList<App> testAppList = new ArrayList<App>();
		testAppList.add(testApp1);
		testAppList.add(testApp2);
		appsListAdapter = new MyAppsListAdapter(this,testAppList);
		
		
		
		}


	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current tab position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current tab position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());

	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		// When the given tab is selected, show the tab contents in the
		// container view.
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		Log.d("Tab selected", "tab has been selected"); 
		Fragment publishPage1Fragment = new PublishPage1(); //the fragment that we want to create and show 
		Fragment myApps1Fragment = new MyApps1();
		fm = getSupportFragmentManager();
		android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction(); //for the Transaction between fragments
		if(tab.getPosition()==TAB_PUBLISH){ //check to see which tab has been selected
			Log.d("Tab selected", "publish tab");
			transaction.replace(R.id.developer_container, publishPage1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			
			
			transaction.commit();
		}
		if(tab.getPosition()==TAB_MYAPPS){ //check to see which tab has been selected
			Log.d("Tab selected", "myapps tab");
			transaction.replace(R.id.developer_container,myApps1Fragment); // the container in Main page and the fragment so it starts the fragment in the container
			transaction.commit();
			
			
			
			
		}

		
	}
	
	public static void changeToPublish2Fragment(){ 
		Fragment publish2Fragment = new PublishPage2();
		android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.developer_container,publish2Fragment);
		transaction.commit();
	}
	public static void changeToPublish3Fragment() {
		Fragment publish3Fragment = new PublishPage3();
		android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.developer_container,publish3Fragment);
		transaction.commit();
	}


	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	public static class PublishPage1 extends Fragment{
		private static final int SCREEN_SHOT1 = 1;
		private static final int SCREEN_SHOT2 = 2;
		private static final int ICON = 3;
		private static final int APK = 4;
		
		

		public PublishPage1() {
			// TODO Auto-generated constructor stub
			
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			return inflater.inflate(R.layout.publish_page1, container, false);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			
			super.onActivityCreated(savedInstanceState);
			
			
			//an intent for file browsing
	        final Intent intent = new Intent();
	        intent.setAction(Intent.ACTION_GET_CONTENT);
	        intent.addCategory(Intent.CATEGORY_OPENABLE);
	        intent.setType("file/*");
	        
	        // SCREEN_SHOT1 browse
			Button browseScreenShot1= (Button)this.getView().findViewById(R.id.screenShot1_bt);
			browseScreenShot1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, SCREEN_SHOT1);
				}
			});
			
	        // SCREEN_SHOT2 browse
			Button browseScreenShot2= (Button)this.getView().findViewById(R.id.screenShot2_bt);
			browseScreenShot2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, SCREEN_SHOT2);
				}
			});
			
			// ICON browse
			Button browseIcon= (Button)this.getView().findViewById(R.id.icon_bt);
			browseIcon.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, ICON);
				}
			});
			
			// APK browse
			Button browseApk= (Button)this.getView().findViewById(R.id.apk_bt);
			browseApk.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					startActivityForResult(intent, APK);
				}
			});
			
			Button next= (Button)this.getView().findViewById(R.id.next_bt);
			next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					DeveloperMain.changeToPublish2Fragment();
				}
			});
			
		}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		EditText screenShot1Uri = (EditText)this.getView().findViewById(R.id.screenShot1_et);
		EditText screenShot2Uri = (EditText)this.getView().findViewById(R.id.screenShot2_et);
		EditText iconUri = (EditText)this.getView().findViewById(R.id.icon_et);
		EditText apkUri= (EditText)this.getView().findViewById(R.id.apk_et);
		//for debugging purpose
		ImageView im = (ImageView)this.getView().findViewById(R.id.imageView1);
		
		// Getting the result of Intent
		// ScreenShot 1 and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==SCREEN_SHOT1) {
			screenShot1Uri.setText(data.getData().getPath());
			im.setImageURI(data.getData());
			im.invalidate();
		}
		// ScreenShot 2 and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==SCREEN_SHOT2) {
			screenShot2Uri.setText(data.getData().getPath());
		}
		// icon and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==ICON) {
			iconUri.setText(data.getData().getPath());
		}
		// apk and setting its edittext value
		if (RESULT_OK==resultCode && requestCode==APK) {
			apkUri.setText(data.getData().getPath());
		}
		
	}
	
	
	}

	public static class PublishPage2 extends Fragment{
		ListView askFor;
		Button addToAskForButton;
		CheckBox targetEverydayuser;
		CheckBox targetProfessionals;
		CheckBox targetExpert;
		Button nextBt;
		ArrayList<AskObj> askForList;
		
		public PublishPage2() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			return inflater.inflate(R.layout.publish_page2, container, false);
			
		}
		public void onActivityCreated(Bundle savedInstanceState) {

			super.onActivityCreated(savedInstanceState);
			
			// getting the list view and add button
			askFor = (ListView)this.getView().findViewById(R.id.ask_for_lv);
			addToAskForButton = (Button)this.getView().findViewById(R.id.add_to_ask_for_bt);
			nextBt = (Button)this.getView().findViewById(R.id.next_bt);
			// All to add custom adapter and add more functionality using dialogs 
			final ArrayList<AskObj> askObjectLists = new ArrayList<AskObj>();
			AskForArrayAdapter adapter = new AskForArrayAdapter(getActivity(), askObjectLists);
			askFor.setAdapter(adapter);
			
			addToAskForButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Builder builder = new AlertDialog.Builder(getActivity());
					final EditText et = new EditText(getActivity());
					et.setHint("Enter what you want to ask");
					
					builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							askObjectLists.add(new AskObj(et.getText().toString(), true));
						}
					}).setView(et).setTitle("Ask For ...").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					}).create().show();
				}
			});
			
			nextBt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					DeveloperMain.changeToPublish3Fragment();
				}
			});
			
			// Built in questions for ask
			askObjectLists.add(new AskObj("User Interface", false));
			askObjectLists.add(new AskObj("Performance", false));
			askObjectLists.add(new AskObj("Errors", false));
			
			// Keeping the ask objects
			askForList = askObjectLists;
			
		}

		private static class AskObj {
			/*
			 * An object to keep track of items in the AskFor ListView.
			 * 
			 * This makes the data in the list view not to recycle.
			 * 
			 * Only used for AskFor listView
			 */
			
		    private String text = "" ;
		    private boolean checked = false ;
		    
		    public AskObj(String text, boolean checked) {
				super();
				this.text = text;
				this.checked = checked;
			}
			
		    public String getText() {
				return text;
			}

			public void setText(String text) {
				this.text = text;
			}

			public boolean isChecked() {
				return checked;
			}

			public void setChecked(boolean checked) {
				this.checked = checked;
			}

			public void toggleChecked() {
		      checked = !checked ;
		    }
		}
		private static class AskObjHolder {
			/*
			 *  An object to hold the data regarding the layout of each item in AskFor ListView.
			 *  
			 *  Only used for AskFor listView
			 */
		    private CheckBox checkBox ;
		    private TextView textView ;
		    public AskObjHolder(TextView textView,CheckBox checkBox ) {
		      this.checkBox = checkBox ;
		      this.textView = textView ;
		    }
		    public CheckBox getCheckBox() {
		      return checkBox;
		    }
		    public void setCheckBox(CheckBox checkBox) {
		      this.checkBox = checkBox;
		    }
			public TextView getTextView() {
				return textView;
			}
			public void setTextView(TextView textView) {
				this.textView = textView;
			}
		    
		}
		/** Custom adapter for displaying an array of Ask objects. */
		private static class AskForArrayAdapter extends ArrayAdapter<AskObj> {
		    
			private LayoutInflater inflater;
			
			public AskForArrayAdapter( Context context, List<AskObj> AskList ) {
			  super( context, R.layout.ask_for_list_item, R.id.rowTextView, AskList );
			  // Cache the LayoutInflate to avoid asking for a new one each time.
			  inflater = LayoutInflater.from(context) ;
			}
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
			  // Planet to display
			  AskObj askobject= (AskObj) this.getItem( position ); 
			
			  // The child views in each row.
			  CheckBox checkBox ; 
			  TextView textView ; 
			  
			  // Create a new row view
			  if ( convertView == null ) {
			    convertView = inflater.inflate(R.layout.ask_for_list_item, null);
			    
			    // Find the child views.
			textView = (TextView) convertView.findViewById( R.id.rowTextView );
			checkBox = (CheckBox) convertView.findViewById( R.id.checkBox1 );
			
			// Optimization: Tag the row with it's child views, so we don't have to 
			// call findViewById() later when we reuse the row.
			convertView.setTag( new AskObjHolder(textView,checkBox) );
			
			// If CheckBox is toggled, update the planet it is tagged with.
			    checkBox.setOnClickListener( new View.OnClickListener() {
			      public void onClick(View v) {
			        CheckBox cb = (CheckBox) v ;
			        AskObj askObject= (AskObj) cb.getTag();
			        askObject.setChecked( cb.isChecked() );
			      }
			    });        
			  }
			  // Reuse existing row view
			  else {
			    // Because we use a ViewHolder, we avoid having to call findViewById().
			    AskObjHolder viewHolder = (AskObjHolder) convertView.getTag();
			    checkBox = viewHolder.getCheckBox() ;
			    textView = viewHolder.getTextView() ;
			  }
			
			  // Tag the CheckBox with the Planet it is displaying, so that we can
			  // access the planet in onClick() when the CheckBox is toggled.
			  checkBox.setTag( askobject); 
			  
			  // Display planet data
			  checkBox.setChecked( askobject.isChecked() );
			  textView.setText( askobject.getText() );      
			  
			  return convertView;
		    }
		    
		}
	
	
	}

	public static class PublishPage3 extends Fragment{
		
		EditText howManyReviews;
		EditText minPayAmount;
		EditText maxPayAmount;
		CheckBox agreement;
		public PublishPage3() {
			// TODO Auto-generated constructor stub
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			return inflater.inflate(R.layout.publish_page3, container, false);
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
		}
		
	}
	
	public static class MyApps1 extends Fragment{


		public MyApps1() {
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			return inflater.inflate(R.layout.my_apps1, container, false);
			
		}
		
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			
			super.onActivityCreated(savedInstanceState);
			
			myAppsListView = (ListView) this.getView().findViewById(R.id.appList);
			appsListAdapter.setListView(myAppsListView);
			myAppsListView.setAdapter(appsListAdapter);
		
			 final Intent appIntent = new Intent(getActivity(),ReadReviewActivity.class);
			 myAppsListView.setOnItemClickListener(new OnItemClickListener() {
			        @Override
			        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			        startActivity(appIntent); 
			        }
				});
		}
	}

	
	
}
