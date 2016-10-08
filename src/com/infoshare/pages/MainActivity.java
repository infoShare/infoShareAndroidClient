package com.infoshare.pages;

import java.util.List;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.infoshare.R;
import com.infoshare.adapters.CategoriesListAdapter;
import com.infoshare.adapters.InfoListAdapter;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.parsers.BooleanParser;
import com.infoshare.parsers.CategoryParser;
import com.infoshare.parsers.InformationParser;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.AlertService;
import com.infoshare.util.ProgressTask;
import com.infoshare.util.ToastService;

public class MainActivity extends Activity implements OnMenuItemClickListener {

	private final Context ctx = MainActivity.this;
	private TextView lastUpdate;
	private TextView category;
	private ListView infoList;
	private InfoListAdapter infoListAdapter;
	private IDataRetriever dataRetriever = ServiceProvider.provideDataRetriever();
	private InformationParser informationParser = new InformationParser();
	private CategoryParser categoryParser = new CategoryParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		infoList = (ListView) findViewById(R.id.mainList);
		lastUpdate = (TextView) findViewById(R.id.updateInfo);
		category = (TextView) findViewById(R.id.category);
		updateLastUpdate();
		updateCategory();
		initializeMainList();
	}

	private void updateCategory() {
		category.setText(getCategory());
	}

	private void updateLastUpdate() {
		lastUpdate.setText("Last update\n" + PreferencesService.getLastUpdate(ctx));
	}
	
	public void showMenu(View v) {
		PopupMenu popup = new PopupMenu(this, v);
	    popup.setOnMenuItemClickListener(this);
	    popup.inflate(R.menu.main_menu);
	    popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.addCategory:
	        	addCategory();
	            return true;
	        case R.id.changeCategory:
	        	changeCategory();
	            return true;
	        case R.id.settings:
	        	settings();
	            return true;
	        case R.id.reportProblem:
	        	reportProblem();
	            return true;
	        case R.id.logout:
	        	logout();
	            return true;
	        default:
	            return false;
	    }
	}
	
	private void initializeMainList(){
		dataRetriever.updateInfoList(ctx, category.getText().toString(), new SoapResultHandler<List<Information>>() {
			
			@Override
			public List<Information> parseResponse(SoapObject response) {
				return informationParser.parse(response);
			}
			
			@Override
			public void handle(SoapObject response) {
				List<Information> informations = parseResponse(response);
		 		ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header_layout, infoList, false);
		 		infoList.addHeaderView(headerView, null, false);
		 		infoListAdapter = new InfoListAdapter(ctx, informations);
		 		infoList.setAdapter(infoListAdapter);
			}
		});
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(PreferencesService.getBooleanFromPrefs(ctx, PreferencesService.UPDATE_KEY, false)){
			refreshList();
		}
	}
	
	private void logout() {
		AlertService.displayDialog(ctx, R.string.logoutTitle, R.string.logoutMessage, new Runnable() {
			@Override
			public void run() {
				PreferencesService.clearPreferences(ctx);
				Intent i = new Intent(ctx, LoginActivity.class);                      
				startActivity(i);
				finish();
			}
		});
	}
	
	private void reportProblem(){
		Intent i = new Intent(ctx, ReportProblemActivity.class);                      
		startActivity(i);
	}
	
	private void addCategory(){
		final Dialog dialog = new Dialog(ctx);
		dialog.setContentView(R.layout.add_category);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
		final EditText categoryName = (EditText) dialog.findViewById(R.id.categoryName);
		Button addCategoryBtn = (Button) dialog.findViewById(R.id.addCategory);
		addCategoryBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertService.displayDialog(ctx, R.string.addCategoryTitle, R.string.addCategoryMessage, 
					new Runnable() {
						@Override
						public void run() {
							dataRetriever.addCategory(ctx, categoryName.getText().toString(), new SoapResultHandler<Boolean>() {
								
								@Override
								public Boolean parseResponse(SoapObject response) {
									return new BooleanParser().parse(response);
								}
								
								@Override
								public void handle(SoapObject response) {
									if(parseResponse(response)){
										dialog.dismiss();
									}else{
										ToastService.showToast(ctx, "Error occured");
									}
									
								}
							});
						}
					}
				);
			}
		});
		dialog.show();
	}
	
	private void changeCategory(){
		dataRetriever.updateCategories(ctx, new SoapResultHandler<List<Category>>() {
			
			@Override
			public List<Category> parseResponse(SoapObject response) {
				return categoryParser.parse(response);
			}
			
			@Override
			public void handle(SoapObject response) {
				List<Category> categories = parseResponse(response);
				final Dialog dialog = new Dialog(ctx);
				dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
				dialog.setContentView(R.layout.select_category);
				final ListView categoriesList = (ListView) dialog.findViewById(R.id.categoriesList);
				CategoriesListAdapter adapter = new CategoriesListAdapter(ctx, categories, new Runnable() {
							@Override
							public void run() {
								refreshCategory();
								dialog.dismiss();
							}
						});
				categoriesList.setAdapter(adapter);
				dialog.show();			
			}
		});
		
	}
	
	private void settings(){
		ToastService.showToast(ctx, "Settings disabled");
	}
	
	@Override
	public void onBackPressed() {
	    AlertService.displayDialog(ctx, R.string.exitTitle, R.string.exitMessage, new Runnable() {
			@Override
			public void run() {
				finish();
			}
		});
	}
	
	private String getCategory() {
		String noCategory = getResources().getString(R.string.noCategory);
		return PreferencesService.getStringFromPrefs(ctx, PreferencesService.CATEGORY_KEY, noCategory);
	}
	
	public void addInfo(View v){
		Intent intent = new Intent(ctx, ShareInfoActivity.class);
		startActivity(intent);
	}
	
	public void refresh(View v){
		refreshList();
	}
	
	private void refreshList(){
		final Activity activity = (Activity) ctx;
		new ProgressTask(ctx, R.string.refreshTitle, R.string.refreshMessage, new Runnable(){
			@Override
			public void run() {
				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						dataRetriever.updateInfoList(ctx, category.getText().toString(), new SoapResultHandler<List<Information>>() {
							
							@Override
							public List<Information> parseResponse(SoapObject response) {
								return informationParser.parse(response);
							}
							
							@Override
							public void handle(SoapObject response) {
								List<Information> informations = parseResponse(response);
								infoListAdapter = new InfoListAdapter(ctx, informations);
								infoList.setAdapter(infoListAdapter);
						  		PreferencesService.saveToPrefs(ctx, PreferencesService.UPDATE_KEY, false);
						  		updateLastUpdate();
							}
						});
						
					}
				});
				
			}
		}).execute();
	}
	
	private void refreshCategory(){
		category.setText(getCategory());
		category.refreshDrawableState();
		refreshList();
	}
	
}
