package com.infoshare.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infoshare.R;
import com.infoshare.model.Information;
import com.infoshare.pages.InformationActivity;
import com.infoshare.util.DateUtils;

public class InfoListAdapter extends ArrayAdapter<Information> {
	
 	private static int groupid = R.layout.row_layout;
 	private static int id = R.id.infoContent;
 	private List<Information> informations;
 	private Context ctx;
 	
 	public InfoListAdapter(Context context, List<Information> informations){
 		super(context, groupid, id, informations);
 		this.ctx = context;
 		this.informations = informations;
 	}

 	static class ViewHolder {
 	   public TextView information;
 	   public TextView date;
 	}	
 
 	public View getView(int position, View convertView, ViewGroup parent) {
	 	View rowView = convertView;
	 	if(rowView==null){
	 		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		rowView= inflater.inflate(groupid, parent, false); 
	 		ViewHolder viewHolder = new ViewHolder();      
	        viewHolder.information = (TextView) rowView.findViewById(R.id.infoContent);
	        viewHolder.date = (TextView) rowView.findViewById(R.id.infoDate);
	        rowView.setTag(viewHolder);     			
	    }
	 	
	 	final Information info = informations.get(position);
	 	ViewHolder holder = (ViewHolder) rowView.getTag();
	 	holder.information.setText(info.getContent());
	 	holder.date.setText(DateUtils.convertDate(info.getCreationDate()));
	 	rowView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ctx, InformationActivity.class);
				intent.putExtra(Information.INFO_KEY, info);
				ctx.startActivity(intent);	
			}
		});
	    return rowView;
    }
 
 	public List<Information> getInformations() {
		return informations;
	}
 	
 	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}
 	
 }