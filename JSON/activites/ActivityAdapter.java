package com.rahul.cheerfoolz.activites;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.cheerfoolz.R;
import com.rahul.cheerfoolz.utils.ImageLoader;

public class ActivityAdapter extends BaseAdapter{

	private Activity context;
	ArrayList<Object> listItems;
	private LayoutInflater inflater;
	ImageLoader loader;
	
    public ActivityAdapter(Activity activity,ArrayList<Object> listItems) {  
        super();  
  
        this.context = activity;
        this.listItems = listItems;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        loader = new ImageLoader(context);
    } 
    
    @Override  
    public int getCount() {  
        // TODO Auto-generated method stub  
        
        return listItems.size();
    }  
  
    @Override  
    public Object getItem(int position) {  
        // TODO Auto-generated method stub
    	return listItems.get(position);
           
    }  
  
    @Override  
    public long getItemId(int position) {  
        // TODO Auto-generated method stub  
        return position;  
    }  
  
    public static class ViewHolder  
    {  
        ImageView imgViewLogo;  
        TextView txtViewTitle; 
    }  
  
    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {  
        // TODO Auto-generated method stub  
    	
    	 ViewHolder holder;  
        if(convertView==null)  
        {  
            holder = new ViewHolder();  
            convertView = inflater.inflate(R.layout.activity_listitem, null); 
       
             holder.imgViewLogo = (ImageView) convertView.findViewById(R.id.activity_list_logo);  
            holder.txtViewTitle = (TextView) convertView.findViewById(R.id.activity_list_title);  
         
            convertView.setTag(holder);  
        }  
        else  
            holder=(ViewHolder)convertView.getTag();  
  
        ActivitiesBean bean = (ActivitiesBean) listItems.get(position);
        
        
        holder.txtViewTitle.setText(bean.getTitle());
              
        holder.imgViewLogo.setTag(bean.getImage());
        loader.DisplayImage(bean.getImage(), holder.imgViewLogo);
        
        
        return convertView;  
    }   
  
}
