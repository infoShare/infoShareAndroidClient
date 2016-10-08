package com.infoshare.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infoshare.R;
import com.infoshare.model.Category;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.util.AlertService;

public class CategoriesListAdapter extends ArrayAdapter<Category> {
	
 	private static int groupId = android.R.layout.simple_list_item_1;
	private static int id = R.id.list_item;
 	private List<Category> categories;
 	private Context ctx;
 	private Runnable task;
 	
 	public CategoriesListAdapter(Context context, List<Category> categories, Runnable task){
 		super(context, groupId, id, categories);
 		this.ctx = context;
 		this.categories = categories;
 		this.task = task;
 	}

 	static class ViewHolder {
 	   public TextView category;
 	}	
 
 	public View getView(int position, View convertView, ViewGroup parent) {
	 	View rowView = convertView;
	 	if(rowView == null){
	 		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 		rowView = inflater.inflate(groupId, parent, false); 
	 		ViewHolder viewHolder = new ViewHolder();      
	        viewHolder.category = (TextView) rowView.findViewById(android.R.id.text1);
	        rowView.setTag(viewHolder);     			
	    }
	 	
	 	final Category category = categories.get(position);
	 	ViewHolder holder = (ViewHolder) rowView.getTag();
	 	holder.category.setText(category.getName());
	 	rowView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertService.displayDialog(ctx, R.string.changeCategoryTitle, R.string.changeCategoryMessage, new Runnable() {
					
					@Override
					public void run() {
						PreferencesService.saveToPrefs(ctx, PreferencesService.CATEGORY_KEY, category.getName());
						task.run();
					}
				});
			}
		});
	    return rowView;
    }
 
 	public List<Category> getCategories() {
		return categories;
	}
 	
 	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
 	
 }