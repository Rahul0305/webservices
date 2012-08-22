package com.rahul.cheerfoolz.activites;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.rahul.cheerfoolz.CheerfoolznativeActivity;
import com.rahul.cheerfoolz.R;
import com.rahul.cheerfoolz.api.API;
import com.rahul.cheerfoolz.api.util;

public class Activites_Activity extends CheerfoolznativeActivity {

	private ListView contests_listView;
	private ProgressBar pgb;
	ActivitiesBean bean;
	ArrayList<Object> listActivities;
	ActivityAdapter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_listview);
		setHeader("Activites",true);
		
		
		contests_listView = (ListView) findViewById(R.id.activity_listView);
		pgb = (ProgressBar) findViewById(R.id.contests_progressBar);
		listActivities = new ArrayList<Object>();
		
		new FetchActivitesTask().execute();
		
			
	}
	
	public class FetchActivitesTask extends AsyncTask<Void, Void, Void> {
		
		int i =0;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 pgb.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			
			String url = "url";
			String strResponse = util.makeWebCall(url);
					
			try {
				JSONObject objResponse = new JSONObject(strResponse);

		    	JSONArray jsonnodes = objResponse.getJSONArray(API.cheerfoolz_activities.NODES);
		
		    	for (i = 0; i < jsonnodes.length(); i++) 
				{
					String str = Integer.toString(i); 
					Log.i("Value of i",str);
					
					JSONObject jsonnode = jsonnodes.getJSONObject(i); 
		             
					JSONObject jsonnodevalue = jsonnode.getJSONObject(API.cheerfoolz_activities.NODE);
					
					bean = new ActivitiesBean();
					
					bean.title = jsonnodevalue.getString(API.cheerfoolz_activities.TITLE);
					bean.image = jsonnodevalue.getString(API.cheerfoolz_activities.FIELD_ACTIVITY_IMAGE_FID);
					bean.tid = jsonnodevalue.getString(API.cheerfoolz_activities.TID);
					bean.Goal = jsonnodevalue.getString(API.cheerfoolz_activities.GOAL);
					bean.WhoCanPlay = jsonnodevalue.getString(API.cheerfoolz_activities.WHO_CAN_PLAY_IT);
					bean.Player = jsonnodevalue.getString(API.cheerfoolz_activities.FIELD_ACITIVITY_PLAYER_NO_VALUE);
					bean.Duration = jsonnodevalue.getString(API.cheerfoolz_activities.DURATION);
					bean.Things = jsonnodevalue.getString(API.cheerfoolz_activities.THINGS_REQUIRED);
					bean.Rules = jsonnodevalue.getString(API.cheerfoolz_activities.RULES);
					bean.HowToPlay = jsonnodevalue.getString(API.cheerfoolz_activities.HOW_TO_PLAY);
					
					listActivities.add(bean);
					
					
				}				
			}
			catch (JSONException e) {
				
				e.printStackTrace();
		  }
			
			return null;
		}

		@Override
		public void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			pgb.setVisibility(View.GONE);
			displayAdapter();
		}
	}
	
	public void displayAdapter()
	{
		adapter = new ActivityAdapter(this, listActivities);
		contests_listView.setAdapter(adapter);
		contests_listView.setOnItemClickListener(new OnItemClickListener() {

			//@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				
				bean = (ActivitiesBean) adapter.getItem(position);
				 				
				Intent in1 = new Intent(Activites_Activity.this, Activity_display.class);
				in1.putExtra("ActivityObject", bean);
				startActivity(in1);
			}
			
		});
		
	}
}
