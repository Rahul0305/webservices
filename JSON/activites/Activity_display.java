package com.rahul.cheerfoolz.activites;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.cheerfoolz.CheerfoolznativeActivity;
import com.rahul.cheerfoolz.R;
import com.rahul.cheerfoolz.utils.ImageLoader;

public class Activity_display extends CheerfoolznativeActivity{

	TextView txt_title,txt_goal,txt_whoPlay,txt_NoOfPlayers,txt_Duration,txt_ThinsRequired,txt_Rules,txt_HowPlay,txt_tid;
	ImageView imglogo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display);
		setHeader("Activites",true);
		
		ImageLoader loader = new ImageLoader(this);
		
		txt_title = (TextView)findViewById(R.id.activity_display_title);
		txt_tid =(TextView)findViewById(R.id.activity_display_tid);
		txt_goal = (TextView)findViewById(R.id.activity_display_goal);
		txt_whoPlay = (TextView)findViewById(R.id.activity_display_whoplay);
		txt_NoOfPlayers = (TextView)findViewById(R.id.activity_display_NoOfPlayer);
		txt_Duration = (TextView)findViewById(R.id.activity_display_Duration);
		txt_ThinsRequired = (TextView)findViewById(R.id.activity_display_ThingsRequired);
		txt_Rules = (TextView)findViewById(R.id.activity_display_Rules);
		txt_HowPlay = (TextView)findViewById(R.id.activity_display_Howplay);
		
		imglogo = (ImageView)findViewById(R.id.activity_display_logo);
		
		ActivitiesBean bean =  (ActivitiesBean) getIntent().getSerializableExtra("ActivityObject");
		
		
		imglogo.setTag(bean.getImage());
        loader.DisplayImage(bean.getImage(),imglogo);
		
		txt_title.setText(bean.getTitle());
		txt_tid.setText(bean.getTid());
		txt_goal.setText(bean.getGoal());
		txt_whoPlay.setText(bean.getWhoCanPlay());
		txt_NoOfPlayers.setText(bean.getPlayer());
		txt_Duration.setText(bean.getDuration());
		txt_ThinsRequired.setText(bean.getThings());
		txt_Rules.setText(bean.getRules());
		txt_HowPlay.setText(bean.getHowToPlay());
				
			
	}
}
